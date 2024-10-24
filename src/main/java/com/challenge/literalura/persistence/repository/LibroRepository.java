package com.challenge.literalura.persistence.repository;

import com.challenge.literalura.persistence.entity.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {

  Boolean existsByTitleIgnoreCase(String title);

  Libro findByTitleIgnoreCase(String title);

  List<Libro> findLibrosByLanguage(String language);
}
