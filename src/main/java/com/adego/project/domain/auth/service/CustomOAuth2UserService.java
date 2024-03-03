package com.adego.project.domain.auth.service;

import com.adego.project.domain.auth.presentation.dto.CustomOAuth2User;
import com.adego.project.domain.auth.presentation.dto.UserDto;
import com.adego.project.domain.auth.presentation.dto.response.OAuth2Response;
import com.adego.project.domain.auth.presentation.dto.response.kakao.KakaoResponse;
import com.adego.project.domain.user.User;
import com.adego.project.domain.user.repository.UserRepository;
import com.adego.project.domain.user.types.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
  private final UserRepository userRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    System.out.println("카카오 로그인 접속");
    log.info("userRequest = {}", userRequest);
    OAuth2User oAuth2User = super.loadUser(userRequest);
    log.info("oAuth2User = {}", oAuth2User);
    System.out.println("oAuth2User = " + oAuth2User);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    System.out.println(registrationId);

    OAuth2Response oAuth2Response = null;
    if (registrationId.equals("kakao")) {
      oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
      System.out.println("oAuth2Response = " + oAuth2Response);
    }

    String userName = oAuth2Response.provider() + oAuth2Response.getProviderId();
    System.out.println("userName = " + userName);

    User userExist = userRepository.findByName(userName);
    System.out.println("userExist = " + userExist);

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
      return new CustomOAuth2User(userDto);
    }

    userExist.setEmail(oAuth2Response.getEmail());
    userExist.setName(oAuth2User.getName());

    userRepository.save(userExist);

    UserDto userDto = new UserDto();
    userDto.setUserName(userName);
    userDto.setName(oAuth2Response.getName());

    return new CustomOAuth2User(userDto);
  }
}
