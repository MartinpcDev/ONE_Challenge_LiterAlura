package com.challenge.literalura.service.impl;

import com.challenge.literalura.service.Consumer;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class APIconsumer implements Consumer {

  @Value("${ntegration.api.base-path}")
  private String BASE_URL;
  private final DataConverter dataConverter = new DataConverter();

  @Override
  public <T> T getData(String params, Class<T> entidad) {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(
            BASE_URL.concat(params != null ? "?" : "").concat(params != null ? params : "")))
        .build();
    HttpResponse<String> response;
    try {
      response = client.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("No se pudo obtener la data: " + e.getMessage());
    }
    return dataConverter.getData(response.body(), entidad);
  }
}
