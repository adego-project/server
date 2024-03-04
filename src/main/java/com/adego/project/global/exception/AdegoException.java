package com.adego.project.global.exception;

import com.adego.project.global.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdegoException extends RuntimeException {
  private final ErrorCode errorCode;
}
