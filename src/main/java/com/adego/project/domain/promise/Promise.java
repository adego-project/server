package com.adego.project.domain.promise;

import com.adego.project.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Promise {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "promise_id")
  private Long id;
  @Column(nullable = false)
  private String promiseName;
  @Column(nullable = false)
  private LocalDate promiseDate;
  @Column(nullable = false)
  private LocalTime promiseTime;

  private Double latitude; // 위도
  private Double longitude; // 경도

  @ManyToOne
  @JoinColumn(name = "name")
  private User user;

  public void setUser(User user) {
    this.user = user;
  }

  @Builder
  public Promise(Long id, String promiseName, LocalDate promiseDate, LocalTime promiseTime, Double latitude, Double longitude, User user) {
    this.id = id;
    this.promiseName = promiseName;
    this.promiseDate = promiseDate;
    this.promiseTime = promiseTime;
    this.latitude = latitude;
    this.longitude = longitude;
    this.user = user;
  }
}
