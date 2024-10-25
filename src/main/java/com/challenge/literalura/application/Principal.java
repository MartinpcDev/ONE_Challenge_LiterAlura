package com.challenge.literalura.application;

import com.challenge.literalura.persistence.entity.Autor;
import com.challenge.literalura.persistence.entity.Libro;
import com.challenge.literalura.service.CatalogoService;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

  private final Scanner consola = new Scanner(System.in);

  private final CatalogoService catalogoService;

  public Principal(CatalogoService catalogoService) {
    this.catalogoService = catalogoService;
  }

  public void execMenu() {
    System.out.println("Menu de opciones");
    Boolean salir = false;
    while (!salir) {
      int opcion = showMenu(consola);
      salir = execOptions(opcion, consola, catalogoService);
      System.out.println();
    }
  }


  private int showMenu(Scanner consola) {
    System.out.print("""
        *** Catalogo de libros - LiterAlura ***
        1. Buscar Libro por Titulo.
        2. Listar Libros registrados.
        3. Listar Autores Registrados.
        4. Listar Autores vivos en un determinado año.
        5. Listar Libros por idioma.
        6. Generando Estadisticas
        7. Top 10 libros mas Descargados
        8. Buscar Autor por Nombre
        9. Buscar Autor fallecido por determinado año
        0. Salir
        Elige una opcion:\s""");
    return Integer.parseInt(consola.nextLine());
  }

  private Boolean execOptions(int opcion, Scanner consola, CatalogoService catalogoService) {
    boolean salir = false;
    switch (opcion) {
      case 1 -> {
        System.out.println("--Buscador de Libros por Titulo--");
        System.out.print("Introduzca el titulo a Buscar: ");
        String titulo = consola.nextLine();
        Libro libro = catalogoService.getLibroByTitle(titulo);
        System.out.println("Titulo: " + titulo);
        System.out.println("Libro: " + libro);
      }
      case 2 -> {
        System.out.println("--Lista de libros Registrados--");
        List<Libro> libros = catalogoService.getAllBooksRegistered();
        if (!libros.isEmpty()) {
          libros.forEach(System.out::println);
        } else {
          System.out.println("No hay libros registrados en la Base de datos");
        }
      }
      case 3 -> {
        System.out.println("--Lista de Autores Registrados--");
        List<Autor> autors = catalogoService.getAllAuthorRegistered();
        if (!autors.isEmpty()) {
          autors.forEach(System.out::println);
        } else {
          System.out.println("No hay autores registrados en la base de datos");
        }
      }
      case 4 -> {
        System.out.println("--Listar Autores Vivos por Año--");
        System.out.print("Ingrese el año: ");
        Integer fecha = Integer.parseInt(consola.nextLine());
        System.out.println("Año Seleccionado: " + fecha);
        List<Autor> autors = catalogoService.getAllAuthorLivesByYear(fecha);
        if (!autors.isEmpty()) {
          autors.forEach(System.out::println);
        } else {
          System.out.println("No hay autores registrados vivos en el año " + fecha);
        }
      }
      case 5 -> {
        System.out.println("--Listar Libros por Language--");
        System.out.print("""
            Formatos Seleccionados
            * en - English
            * es - Español
            """);
        System.out.print("Ingrese el Lenguaje a buscar: ");
        String language = consola.nextLine();
        System.out.println("Lenguaje seleccionado: " + language);
        List<Libro> libros = catalogoService.getAllBooksByLanguage(language);
        if (!libros.isEmpty()) {
          libros.forEach(System.out::println);
        } else {
          System.out.println("No hay libros con el language " + language + " seleccionado");
        }
      }
      case 6 -> {
        System.out.println("--Generando Estadisticas--");
        List<Libro> libros = catalogoService.getAllBooksRegistered();
        IntSummaryStatistics estadistics = libros.stream()
            .filter(l -> l.getDownloads() > 0)
            .collect(Collectors.summarizingInt(Libro::getDownloads));
        System.out.println("Media de Downloads: " + estadistics.getAverage());
        System.out.println("Mayor numero de descargas por libros: " + estadistics.getMax());
        System.out.println("Menor numero de descargas por libro: " + estadistics.getMin());
        System.out.println("Cantidad: " + estadistics.getCount());
      }
      case 7 -> {
        System.out.println("--Top 10 libros mas Descargados--");
        List<Libro> libros = catalogoService.get10BooksByDownloads();
        libros.forEach(System.out::println);
      }
      case 8 -> {
        System.out.println("--Buscar Autor por Nombre--");
        System.out.print("Ingrese el Nombre a buscar: ");
        String name = consola.nextLine();
        Autor autor = catalogoService.getAutorByName(name);
        if (autor != null) {
          System.out.println(autor);
        } else {
          System.out.println("El autor no esta en la base de datos");
        }
      }
      case 9 -> {
        System.out.println("--Buscar Autor fallecido por determinado año--");
        System.out.print("Ingrese el año: ");
        Integer fecha = Integer.parseInt(consola.nextLine());
        System.out.println("Año Seleccionado: " + fecha);
        List<Autor> autors = catalogoService.getAllAuthorDeadsByYear(fecha);
        if (!autors.isEmpty()) {
          autors.forEach(System.out::println);
        } else {
          System.out.println("No hay autores registrados muertos en el año " + fecha);
        }
      }
      case 0 -> {
        System.out.println("Hasta Pronto");
        salir = true;
      }
      default -> System.out.println("Opcion invalida, Elija otra opcion: " + opcion);
    }
    return salir;
  }
}
