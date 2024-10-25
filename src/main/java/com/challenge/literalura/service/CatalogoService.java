package com.challenge.literalura.service;

import com.challenge.literalura.persistence.entity.Autor;
import com.challenge.literalura.persistence.entity.Libro;
import java.util.List;

public interface CatalogoService {

  Libro getLibroByTitle(String title);

  List<Libro> getAllBooksRegistered();

  List<Autor> getAllAuthorRegistered();

  List<Autor> getAllAuthorLivesByYear(Integer year);

  List<Libro> getAllBooksByLanguage(String language);

  List<Libro> get10BooksByDownloads();

  Autor getAutorByName(String name);

  List<Autor> getAllAuthorDeadsByYear(Integer year);
}
