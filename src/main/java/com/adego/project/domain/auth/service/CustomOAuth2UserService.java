package com.adego.project.domain.auth.service;

import com.adego.project.domain.auth.presentation.dto.CustomOAuth2User;
import com.adego.project.domain.auth.presentation.dto.UserDto;
import com.adego.project.domain.auth.presentation.dto.response.OAuth2Response;
import com.adego.project.domain.auth.presentation.dto.response.google.GoogleResponse;
import com.adego.project.domain.auth.presentation.dto.response.kakao.KakaoResponse;
import com.adego.project.domain.user.User;
import com.adego.project.domain.user.repository.UserRepository;
import com.adego.project.domain.user.types.RoleType;
import com.adego.project.global.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;

  @Value("${jwt.secretKey}")
  private String secret;

  private final Long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60L;
  private final Long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7L;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    System.out.println("카카오 로그인 접속");
    log.info("userRequest = {}", userRequest);
    OAuth2User oAuth2User = super.loadUser(userRequest);
    log.info("oAuth2User = {}", oAuth2User);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    OAuth2Response oAuth2Response = null;
    if (registrationId.equals("kakao")) {
      oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
    } else if (registrationId.equals("google")) {
      oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
    }

    String userName = oAuth2Response.provider() + oAuth2Response.getProviderId();
    System.out.println("userName = " + userName);

    User userExist = userRepository.findByName(userName);
    System.out.println("userExist = " + userExist);

    String accessToken = jwtUtil.createToken(
      oAuth2Response.getName(),
      oAuth2Response.getEmail(),
      secret,
      ACCESS_TOKEN_EXPIRE_TIME
    );

    String refreshToken = jwtUtil.createToken(
      oAuth2Response.getName(),
      oAuth2Response.getEmail(),
      secret,
      REFRESH_TOKEN_EXPIRE_TIME
    );

    if (userExist == null) {
      User user = User.builder()
        .email(oAuth2Response.getEmail())
        .name(oAuth2Response.getName())
        .nickname(userName)
        .profileImage(oAuth2Response.getProfileImage())
        .role(RoleType.ROLE_USER)
        .build();

      userRepository.save(user);

      UserDto userDto = new UserDto();
      userDto.setUserName(userName);
      userDto.setName(oAuth2Response.getName());
      System.out.println("userDto = " + userDto);

      return new CustomOAuth2User(oAuth2Response, RoleType.ROLE_USER, accessToken, refreshToken);
    }

    userExist.setEmail(oAuth2Response.getEmail());
    userExist.setName(oAuth2User.getName());

    userRepository.save(userExist);

    UserDto userDto = new UserDto();
    userDto.setUserName(userName);
    userDto.setName(oAuth2Response.getName());

    return new CustomOAuth2User(oAuth2Response, RoleType.ROLE_USER, accessToken, refreshToken);
  }
}
