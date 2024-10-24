package com.challenge.literalura.service;

import com.challenge.literalura.persistence.entity.Libro;

public interface CatalogoService {

  Libro getLibroByTitle(String title);
}
