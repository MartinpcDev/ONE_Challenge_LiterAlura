package com.challenge.literalura.application;

import com.challenge.literalura.persistence.entity.Libro;
import com.challenge.literalura.service.CatalogoService;
import java.util.Scanner;

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
        6. Salir
        Elige una opcion:\s""");
    return Integer.parseInt(consola.nextLine());
  }

  private Boolean execOptions(int opcion, Scanner consola, CatalogoService catalogoService) {
    boolean salir = false;
    switch (opcion) {
      case 1 -> {
        System.out.println("Buscador de Libros por Titulo");
        System.out.print("Introduzca el titulo a Buscar: ");
        String titulo = consola.nextLine();
        Libro libro = catalogoService.getLibroByTitle(titulo);
        System.out.println("Titulo: " + titulo);
        System.out.println("Libro: " + libro);
      }
      case 6 -> {
        System.out.println("Hasta Pronto");
        salir = true;
      }
      default -> System.out.println("Opcion invalida, Elija otra opcion: " + opcion);
    }
    return salir;
  }
}
