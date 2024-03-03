package com.adego.project.domain.auth.presentation.dto.response.kakao;

import com.adego.project.domain.auth.presentation.dto.response.OAuth2Response;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {
  private final Map<String, Object> attribute;

  public KakaoResponse(Map<String, Object> attribute) {
    this.attribute = attribute;
  }

  @Override
  public String provider() {
    return "kakao";
  }

  @Override
  public String getProviderId() {
    return attribute.get("")
  }

  @Override
  public String getEmail() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }
}
