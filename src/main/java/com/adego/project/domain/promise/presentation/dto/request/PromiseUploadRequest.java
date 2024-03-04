package com.adego.project.domain.promise.presentation.dto.request;

import com.adego.project.domain.promise.Promise;
import com.adego.project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class PromiseUploadRequest {
  private String promiseName;
  private LocalDate promiseDate;
  private LocalTime promiseTime;
  private Double latitude; // 위도
  private Double longitude; // 경도
  private String userName;

  public Promise toEntity(User user) {
    return Promise.builder()
      .promiseName(promiseName)
      .promiseDate(promiseDate)
      .promiseTime(promiseTime)
      .latitude(latitude)
      .longitude(longitude)
      .user(user)
      .build();
  }
}
