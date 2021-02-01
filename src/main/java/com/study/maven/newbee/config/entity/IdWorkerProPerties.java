package com.study.maven.newbee.config.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author HLH
 * @version 1.0
 * @email 17703595860@163.com
 */
@Component
@Data
@ConfigurationProperties(prefix = "idworker")
public class IdWorkerProPerties {

    private Long datacenterId;
    private Long workerId;

}
