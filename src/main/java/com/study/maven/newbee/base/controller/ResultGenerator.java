package com.study.maven.newbee.base.controller;

import com.study.maven.newbee.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 返回结果生成器
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/29 18:02
 */
public interface ResultGenerator {

    /** 200 */
    default ResponseEntity<Result<?>> success() {
        return ResponseEntity.ok(
                Result.builder().message(HttpStatus.OK.getReasonPhrase()).build()
        );
    }

    default ResponseEntity<Result<?>> success(Object data) {
        return ResponseEntity.ok(
                Result.builder().message(HttpStatus.OK.getReasonPhrase()).data(data).build()
        );
    }

    default ResponseEntity<Result<?>> success(String message, Object data) {
        return ResponseEntity.ok(
                Result.builder().message(HttpStatus.OK.getReasonPhrase()).message(message).data(data).build()
        );
    }

    /** 401 */
    default ResponseEntity<Result<?>> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Result.builder().resultCode(HttpStatus.UNAUTHORIZED.value()).message(HttpStatus.UNAUTHORIZED.getReasonPhrase()).build()
        );
    }

    default ResponseEntity<Result<?>> unauthorized(String message) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Result.builder().resultCode(HttpStatus.UNAUTHORIZED.value()).message(message).build()
        );
    }

    default ResponseEntity<Result<?>> unauthorized(String message, Object data) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Result.builder().resultCode(HttpStatus.UNAUTHORIZED.value()).message(HttpStatus.UNAUTHORIZED.getReasonPhrase()).message(message).data(data).build()
        );
    }

    /** 404 */
    default ResponseEntity<Result<?>> notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Result.builder().resultCode(HttpStatus.NOT_FOUND.value()).message(HttpStatus.NOT_FOUND.getReasonPhrase()).build()
        );
    }

    default ResponseEntity<Result<?>> notFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Result.builder().resultCode(HttpStatus.NOT_FOUND.value()).message(message).build()
        );
    }

    default ResponseEntity<Result<?>> notFound(String message, Object data) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Result.builder().resultCode(HttpStatus.NOT_FOUND.value()).message(HttpStatus.NOT_FOUND.getReasonPhrase()).message(message).data(data).build()
        );
    }

    /** 500 */
    default ResponseEntity<Result<?>> internalServererror() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Result.builder().resultCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build()
        );
    }

    default ResponseEntity<Result<?>> internalServererror(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Result.builder().resultCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(message).build()
        );
    }

    default ResponseEntity<Result<?>> internalServererror(String message, Object data) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                Result.builder().resultCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).message(message).data(data).build()
        );
    }

}
