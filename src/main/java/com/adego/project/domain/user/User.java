package com.adego.project.domain.user;

import com.adego.project.domain.promise.Promise;
import com.adego.project.domain.user.types.RoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  @GeneratedValue
  private String id;
  private String provider;
  private String providerId;
  private String email;
  private String name;

  @OneToMany(
    mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<Promise> promiseList;

  public void addPromise(Promise promise) {
    promise.setUser(this);
    this.promiseList.add(promise);
  }

  @Enumerated(EnumType.STRING)
  private RoleType role;

  @Builder
  public User(String provider, String providerId, String email, String name, List<Promise> promiseList, RoleType role) {
    this.provider = provider;
    this.providerId = providerId;
    this.email = email;
    this.name = name;
    this.promiseList = promiseList;
    this.role = role;
  }
}
