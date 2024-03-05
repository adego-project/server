package com.adego.project.domain.auth.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
  private String role;
  private String name;
  private String userName;
  private String access;
  private String refresh;
}
