package com.study.maven.newbee.config.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.annotation.*;

/**
 * 自定义注解 @PageSize 给pageSize入参判断
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 20:09:56
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageSize {

    String value() default "pageSize";

}
