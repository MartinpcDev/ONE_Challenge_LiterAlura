package com.challenge.literalura.service.impl;

import com.challenge.literalura.persistence.dto.AutorData;
import com.challenge.literalura.persistence.dto.BookData;
import com.challenge.literalura.persistence.dto.Data;
import com.challenge.literalura.persistence.entity.Autor;
import com.challenge.literalura.persistence.entity.Libro;
import com.challenge.literalura.persistence.repository.AutorRepository;
import com.challenge.literalura.persistence.repository.LibroRepository;
import com.challenge.literalura.service.CatalogoService;
import com.challenge.literalura.service.Consumer;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogoServiceImpl implements CatalogoService {

  private final String PARAM_SEARCH = "search=";
  private final Consumer consumer;
  private final AutorRepository autorRepository;
  private final LibroRepository libroRepository;

  @Autowired
  public CatalogoServiceImpl(Consumer consumer, AutorRepository autorRepository,
      LibroRepository libroRepository) {
    this.consumer = consumer;
    this.autorRepository = autorRepository;
    this.libroRepository = libroRepository;
  }

  @Override
  public Libro getLibroByTitle(String title) {
    Optional<BookData> bookData = getBookDataApi(title);
    Libro libro = new Libro();

    if (bookData.isPresent() && !libroRepository.existsByTitleIgnoreCase(title)) {
      BookData bookDataExists = bookData.get();
      Autor autor = bookDataExists.authors().stream()
          .map(this::saveOrRecoveryAutor)
          .findFirst()
          .orElseThrow(() -> new RuntimeException("No se encontro el autor"));

      Libro libroApi = new Libro(bookDataExists, autor);
      libro = libroRepository.save(libroApi);
    } else if (libroRepository.existsByTitleIgnoreCase(title)) {
      libro = libroRepository.findByTitleIgnoreCase(title);
    } else {
      System.out.println("Libro no encontrado");
    }

    return libro;
  }

  private Optional<BookData> getBookDataApi(String title) {
    List<BookData> bookData =
        consumer.getData(PARAM_SEARCH + title.replace(" ", "+"), Data.class).results();
    return bookData.stream()
        .filter(b -> b.title().toLowerCase().contains(title.toLowerCase()))
        .findFirst();
  }

  private Autor saveOrRecoveryAutor(AutorData autorData) {
    return autorRepository.findByNameContainsIgnoreCase(autorData.name())
        .orElseGet(() -> {
          Autor newAutor = new Autor(autorData);
          return autorRepository.save(newAutor);
        });
  }
}
