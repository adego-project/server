package com.adego.project.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
      .addMapping("/**")
      .allowedMethods("GET", "POST", "PUT", "DELETE")
      .allowedHeaders("Authorization", "Content-Type")
      .allowedOrigins("*")
      .allowCredentials(true)
      .maxAge(30);
  }
}
