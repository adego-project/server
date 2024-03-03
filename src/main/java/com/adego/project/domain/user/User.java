package com.adego.project.domain.user;

import com.adego.project.domain.user.types.RoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;
  private String email;
  private String name;
  private String password;
  @Enumerated(EnumType.STRING)
  private RoleType role;
}
