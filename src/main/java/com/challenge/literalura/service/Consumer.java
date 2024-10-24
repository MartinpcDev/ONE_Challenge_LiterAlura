package com.challenge.literalura.service;

public interface Consumer {

  <T> T getData(String params, Class<T> entidad);
}
