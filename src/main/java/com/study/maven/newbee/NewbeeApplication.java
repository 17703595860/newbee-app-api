package com.study.maven.newbee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * springBoot启动器
 *
 * @author HLH
 * @version 1.0
 * @email 17703595860@163.com
 */
@SpringBootApplication
@MapperScan(basePackages = "com.study.maven.newbee.mapper")
public class NewbeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewbeeApplication.class, args);
    }

}