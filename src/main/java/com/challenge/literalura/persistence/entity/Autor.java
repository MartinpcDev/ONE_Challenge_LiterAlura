package com.challenge.literalura.persistence.entity;

import com.challenge.literalura.persistence.dto.AutorData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Autor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Integer birthDate;
  private Integer deathDate;
  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Libro> libros;

  public Autor(AutorData autorData) {
    this.name = autorData.name();
    this.birthDate = autorData.birthYear();
    this.deathDate = autorData.deathYear();
  }

  @Override
  public String toString() {
    List<String> autorBooks = libros.stream()
        .map(Libro::getTitle)
        .toList();

    return String.format("""
        --- AUTOR ---
        Nombre: %s
        Fecha de nacimiento: %s
        Fecha de fallecimiento: %s
        Libros: %s
        ------------""", name, birthDate, deathDate, autorBooks);
  }
}
