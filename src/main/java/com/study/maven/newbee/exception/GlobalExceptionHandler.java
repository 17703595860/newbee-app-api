package com.study.maven.newbee.exception;

import com.study.maven.newbee.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/26 21:39:23
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** 401 自定义异常 */
    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<Result<?>> AuthenticationExceptionHandler(AuthenticationException e) {
        e.printStackTrace();
        return ResponseEntity.status(e.getCode()).body(
                Result.builder().resultCode(e.getCode()).message(e.getMessage()).build()
        );
    }

    /** 500 自定义异常 */
    @ExceptionHandler(SystemException.class)
    private ResponseEntity<Result<?>> SystemExceptionHandler(SystemException e) {
        e.printStackTrace();
        return ResponseEntity.status(e.getCode()).body(
                Result.builder().resultCode(e.getCode()).message(e.getMessage()).build()
        );
    }

    /** 500 统一处理运行时异常 */
    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<Result<?>> RuntimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Result.builder().resultCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(e.getMessage()).build()
        );
    }

}
