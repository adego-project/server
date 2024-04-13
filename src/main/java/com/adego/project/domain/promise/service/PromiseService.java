package com.adego.project.domain.promise.service;

import com.adego.project.domain.promise.Promise;
import com.adego.project.domain.promise.presentation.dto.request.PromiseUploadRequest;
import com.adego.project.domain.promise.presentation.dto.response.PromiseResponse;
import com.adego.project.domain.promise.repository.PromiseRepository;
import com.adego.project.domain.user.User;
import com.adego.project.domain.user.repository.UserRepository;
import com.adego.project.global.exception.AdegoException;
import com.adego.project.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromiseService {

  private final PromiseRepository promiseRepository;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<PromiseResponse> get() {
    List<Promise> lists = promiseRepository.findAllByUser();

    List<PromiseResponse> responses = lists.stream()
      .map(
        promise -> {
          String userName = promise.getUser().getName();

          if(userName == null) {
            throw new AdegoException(ErrorCode.BAD_REQUEST_DTO.getMessage());
          }

          return PromiseResponse.builder()
            .promiseName(promise.getPromiseName())
            .promiseDate(promise.getPromiseDate())
            .promiseTime(promise.getPromiseTime())
            .latitude(promise.getLatitude())
            .longitude(promise.getLongitude())
            .userName(userName)
            .build();
        }
      )
      .collect(Collectors.toList());

    return responses;
  }

  @Transactional
  public ResponseEntity<ErrorCode> create(PromiseUploadRequest request) {
    String userName = request.getUserName();
    LocalDate date = request.getPromiseDate();
    LocalTime time = request.getPromiseTime();

    if(userName == null || date == null || time == null) {
      ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorCode.BAD_REQUEST_DTO);
    }

    Promise promise = promiseRepository.findByPromiseName(request.getPromiseName());
    User user = userRepository.findByName(request.getUserName());

    if(promise == null) {
      promise = request.toEntity(user);
      user.addPromise(promise);
    }

    return ResponseEntity.status(HttpStatus.OK).body(ErrorCode.SUCCESS_REQUEST_PROMISE);
  }
}
