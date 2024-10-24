package com.challenge.literalura;

import com.challenge.literalura.application.Principal;
import com.challenge.literalura.service.CatalogoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(LiteraluraApplication.class);
  private final CatalogoService catalogoService;

  public LiteraluraApplication(CatalogoService catalogoService) {
    this.catalogoService = catalogoService;
  }

  public static void main(String[] args) {
    SpringApplication.run(LiteraluraApplication.class, args);
  }

  @Override
  public void run(String... args) {
    logger.info("Iniciando Aplicacion...");
    Principal principal = new Principal(catalogoService);
    principal.execMenu();
    logger.info("Finalizando Aplicacion...");
  }
}
