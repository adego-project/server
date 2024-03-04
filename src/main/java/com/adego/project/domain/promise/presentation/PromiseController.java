package com.adego.project.domain.promise.presentation;

import com.adego.project.domain.promise.presentation.dto.request.PromiseUploadRequest;
import com.adego.project.domain.promise.service.PromiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promise")
@RequiredArgsConstructor
public class PromiseController {
  private final PromiseService promiseService;
  @PostMapping("/upload")
  public ResponseEntity<String> promiseUpload(@RequestBody PromiseUploadRequest request) {
    promiseService.create(request);
    return ResponseEntity.ok("약속 등록을 성공적으로 완료하였습니다");
  }
}
