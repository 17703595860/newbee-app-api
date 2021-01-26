package com.study.maven.newbee.exception;

import com.study.maven.newbee.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/26 21:39:23
 */
//@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<Result<?>> AuthenticationHandler(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Result.builder().resultCode(e.getCode()).message(e.getMessage()).build()
        );
    }

}
