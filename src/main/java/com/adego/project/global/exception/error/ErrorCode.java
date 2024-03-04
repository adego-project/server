package com.adego.project.global.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  // USER
  USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다"),
  TOKEN_EXPIRED(404, "토큰을 찾을 수 없습니다"),
  USER_ALREADY_EXIST(409, "이미 존재하는 사용자입니다"),

  // AUTH
  BAD_REQUEST_AUTH(400, "인증 요청정보가 잘못되었습니다"),
  USER_NOT_CERTIFICATION(403, "로그인이 되어있지 않은 사용자입니다"),

  // PROMISE
  SUCCESS_REQUEST_PROMISE(200, "성공적으로 처리되었습니다"),
  BAD_REQUEST_DTO(400, "요청정보가 잘못되었습니다"),

  // SERVER
  INTERNAL_SERVER_ERROR(500, "내부 서버 오류가 발생했습니다");

  private final int status;
  private final String message;
}
