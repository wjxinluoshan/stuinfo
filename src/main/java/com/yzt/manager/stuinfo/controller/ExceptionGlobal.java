package com.yzt.manager.stuinfo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobal {

  @ExceptionHandler
  public String exception(Exception e) {
    e.printStackTrace();
    return null;
  }
}
