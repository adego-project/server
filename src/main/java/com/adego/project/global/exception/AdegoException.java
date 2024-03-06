package com.adego.project.global.exception;

import com.adego.project.global.exception.error.ErrorCode;
import lombok.Getter;

@Getter
public class AdegoException extends RuntimeException {
  private ErrorCode errorCode = null;

  public AdegoException(String message) {
  }
}
