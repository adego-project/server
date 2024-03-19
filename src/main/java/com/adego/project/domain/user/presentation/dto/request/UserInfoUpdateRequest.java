package com.adego.project.domain.user.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdateRequest {
  private String nickName;
  private String password;
  private String email;
}
