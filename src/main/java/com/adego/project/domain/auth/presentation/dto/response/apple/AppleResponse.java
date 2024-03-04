package com.adego.project.domain.auth.presentation.dto.response.apple;

import com.adego.project.domain.auth.presentation.dto.response.OAuth2Response;

import java.util.Map;

public class AppleResponse implements OAuth2Response {
  private final Map<String, Object> attribute;

  public AppleResponse(Map<String, Object> attribute) {
    this.attribute = attribute;
  }

  @Override
  public String provider() {
    return "Apple";
  }

  @Override
  public String getProviderId() {
    return attribute.get("").toString(); // TODO: 여기에 providerId를 응답하는 형식을 보고나서 작성해야함
  }

  @Override
  public String getEmail() {
    return attribute.get("").toString(); // TODO: 여기에 email을 응답하는 형식을 보고나서 작성해야함
  }

  @Override
  public String getName() {
    return attribute.get("").toString(); // TODO: 여기에 name을 응답하는 형식을 보고나서 작성해야함
  }

  @Override
  public String getProfileImage() {
    return null;
  }
}
