package com.adego.project.domain.user;

import com.adego.project.domain.promise.Promise;
import com.adego.project.domain.user.types.RoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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
  private String nickname;
  private String password;
  @Column(columnDefinition = "LONGBLOB")
  private String profileImage;

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
  public User(Long id, String email, String name, String nickname, String password, String profileImage, List<Promise> promiseList, RoleType role) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.nickname = nickname;
    this.password = password;
    this.profileImage = profileImage;
    this.promiseList = promiseList;
    this.role = role;
  }
}
