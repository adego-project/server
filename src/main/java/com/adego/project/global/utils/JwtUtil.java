package com.adego.project.global.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

  public static String createToken(
    String name, String email, String secret, Long exprTime
  ) {
    Claims claims = Jwts.claims();
    claims.put(name, email);

    return Jwts.builder()
      .setClaims(claims)
      .setIssuedAt(new Date(System.currentTimeMillis()))
      .setExpiration(new Date(System.currentTimeMillis() + exprTime))
      .signWith(SignatureAlgorithm.HS256, secret)
      .compact();
  }
}
