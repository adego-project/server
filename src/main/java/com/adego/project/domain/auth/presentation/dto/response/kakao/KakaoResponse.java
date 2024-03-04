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
    return (String) attribute.get("id");
  }

  @Override
  public String getEmail() {
    Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
    return (String) kakaoAccount.get("email");
  }

  @Override
  public String getName() {
    Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
    Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
    return (String) profile.get("name");
  }

  public String getProfileImage() {
    Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
    Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
    return (String) profile.get("profile_image_url");
  }
}
