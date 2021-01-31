package com.study.maven.newbee.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/31 15:49:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = -683508565712293836L;

    private final int code = 500;

    public SystemException(String message) {
        super(message);
    }
}
