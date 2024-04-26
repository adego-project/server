package com.adego.project.domain.promise.presentation;



import com.adego.project.domain.promise.presentation.dto.request.PromiseUploadRequest;
import com.adego.project.domain.promise.presentation.dto.response.PromiseResponse;
import com.adego.project.domain.promise.service.PromiseService;
import com.adego.project.global.exception.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promise")
@RequiredArgsConstructor
public class PromiseController {
  private final PromiseService promiseService;
  @GetMapping("/read")
  public List<PromiseResponse> getPromise() {
    List<PromiseResponse> responseList = promiseService.get();
    return responseList;
  }

  @PostMapping("/upload")
  public ResponseEntity<ErrorCode> promiseUpload(@RequestBody PromiseUploadRequest request) {
    promiseService.create(request);
    return ResponseEntity.ok(ErrorCode.SUCCESS_REQUEST_PROMISE);
  }
}
