package com.study.maven.newbee.exception;

import lombok.*;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/26 21:36:17
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationException extends RuntimeException {

    private final int code = 401;

    private static final long serialVersionUID = 7800795485733505671L;

    public AuthenticationException() {

    }

    public AuthenticationException(String message) {
        super(message);
    }

}
