package com.adego.project.domain.auth.service;

import com.adego.project.domain.auth.presentation.dto.response.OAuth2Response;
import com.adego.project.domain.auth.presentation.dto.response.kakao.KakaoResponse;
import com.adego.project.domain.user.User;
import com.adego.project.domain.user.repository.UserRepository;
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
    log.info("userRequest = {}", userRequest);
    OAuth2User oAuth2User = super.loadUser(userRequest);
    log.info("oAuth2User = {}", oAuth2User);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();

    OAuth2Response oAuth2Response = null;
    if(registrationId.equals("kakao")) {
      oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
    }

    String userName = oAuth2Response.provider() + oAuth2Response.getProviderId();

    boolean userExist = userRepository.existsByUserName(userName);

    if (!userExist) {

    }
  }
}
