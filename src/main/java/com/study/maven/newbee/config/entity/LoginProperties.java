package com.study.maven.newbee.config.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/29 18:50
 */
@Slf4j
@Component
@Data
@ConfigurationProperties(prefix = "login")
public class LoginProperties implements Serializable {
    private static final long serialVersionUID = 9161965362761200699L;

    private List<String> loginRelease;

}
