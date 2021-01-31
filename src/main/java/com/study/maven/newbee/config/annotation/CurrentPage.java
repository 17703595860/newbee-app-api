package com.study.maven.newbee.config.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解 @CurrentPage 给currentPage入参判断
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 20:09:56
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentPage {

    String value() default "currentPage";

}
