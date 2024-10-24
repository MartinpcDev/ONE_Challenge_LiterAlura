package com.challenge.literalura.persistence.entity;

import com.challenge.literalura.persistence.dto.BookData;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Libro {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String language;
  private Integer downloads;
  @ManyToOne
  @JoinColumn(name = "autor_id", nullable = false)
  private Autor autor;


  public Libro(BookData bookData, Autor autor) {
    this.title = bookData.title();
    this.language = bookData.languages().get(0);
    this.downloads = bookData.downloads();
    this.autor = autor;
  }


  @Override
  public String toString() {
    return String.format("""
        --- LIBRO ---
        Título: %s
        Autor: %s
        Idioma: %s
        Número de descargas: %s
        ------------""", title, autor.getName(), language, downloads);
  }
}
