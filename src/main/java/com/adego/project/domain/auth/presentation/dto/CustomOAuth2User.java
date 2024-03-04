package com.adego.project.domain.auth.presentation.dto;

import com.adego.project.domain.auth.presentation.dto.response.OAuth2Response;
import com.adego.project.domain.user.types.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {
  private final OAuth2Response oAuth2Response;
  private final RoleType role;


  @Override
  public Map<String, Object> getAttributes() {
    return null;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collection = new ArrayList<>();

    collection.add((GrantedAuthority) role::name);

    return collection;
  }

  @Override
  public String getName() {
    return oAuth2Response.getName();
  }
}
