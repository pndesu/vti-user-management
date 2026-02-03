package org.example.user_management_2.exception;

import org.example.user_management_2.common.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<BaseResponse<Object>> handleBusinessException(BusinessException e){
    if (e.getMessage() != null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>(null, e.getMessage()));
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>(null, "System error, please try again later"));
    }
  }
}
