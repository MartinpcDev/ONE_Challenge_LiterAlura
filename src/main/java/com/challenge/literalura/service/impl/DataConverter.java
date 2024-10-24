package com.challenge.literalura.service.impl;

import com.challenge.literalura.service.Consumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConverter implements Consumer {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public <T> T getData(String json, Class<T> entidad) {
    try {
      return mapper.readValue(json, entidad);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("No fue posible convertir el Json :" + e.getMessage());
    }
  }
}
