package com.adego.project.domain.promise.presentation.dto.request;

import com.adego.project.domain.promise.Promise;
import com.adego.project.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PromiseUploadRequest {
  @NotBlank(message = "이름은 필수값입니다")
  private String promiseName;
  @NotBlank(message = "날짜는 필수값입니다")
  private LocalDate promiseDate;
  @NotBlank(message = "시간은 필수값입니다")
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
