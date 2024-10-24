package com.challenge.literalura.persistence.repository;

import com.challenge.literalura.persistence.entity.Autor;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {

  Optional<Autor> findByNameContainsIgnoreCase(String name);
}