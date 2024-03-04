package com.adego.project.domain.promise.service;

import com.adego.project.domain.promise.Promise;
import com.adego.project.domain.promise.presentation.dto.request.PromiseUploadRequest;
import com.adego.project.domain.promise.repository.PromiseRepository;
import com.adego.project.domain.user.User;
import com.adego.project.domain.user.repository.UserRepository;
import com.adego.project.global.exception.AdegoException;
import com.adego.project.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class PromiseService {

  private final PromiseRepository promiseRepository;
  private final UserRepository userRepository;

  public ResponseEntity<ErrorCode> create(PromiseUploadRequest request) {
    String userName = request.getUserName();
    LocalDate date = request.getPromiseDate();
    LocalTime time = request.getPromiseTime();

    if(userName == null || date == null || time == null) {
      ResponseEntity.status(400).body(ErrorCode.BAD_REQUEST_DTO);
    }

    Promise promise = promiseRepository.findByPromiseName(request.getPromiseName());
    User user = userRepository.findByName(request.getUserName());

    if(promise == null) {
      promise = request.toEntity(user);
      user.addPromise(promise);
      promiseRepository.save(promise);
    }

    return ResponseEntity.status(200).body(ErrorCode.SUCCESS_REQUEST_PROMISE);
  }
}
