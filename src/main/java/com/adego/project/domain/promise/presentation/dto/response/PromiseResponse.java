package com.adego.project.domain.promise.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@Builder
public class PromiseResponse {
  private String promiseName;
  private LocalDate promiseDate;
  private LocalTime promiseTime;
  private Double latitude; // 위도
  private Double longitude; // 경도
  private String userName;
}
