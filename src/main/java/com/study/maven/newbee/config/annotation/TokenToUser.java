package com.study.maven.newbee.config.annotation;

import com.study.maven.newbee.entity.User;

import java.lang.annotation.*;

/**
 * 自定义注解，作用于方法，给方法注入参数
 *  基于aop 和注解方式，进行 方法拦截
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/29 15:44
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenToUser {

    String value() default "user";

}
