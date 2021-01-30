package com.study.maven.newbee.config.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 常量类，在配置文件中读取
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 13:27:58
 */
@Data
@Component
@ConfigurationProperties(prefix = "constants")
public class Constants implements Serializable {
    private static final long serialVersionUID = 3970888012939203719L;

    private Integer indexNum;

}
