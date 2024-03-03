package com.adego.project.domain.auth.presentation.dto.response;

public interface OAuth2Response {
  String provider();
  String getProviderId();
  String getEmail();
  String getName();
  String getProfileImage();
}
