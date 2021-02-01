package com.study.maven.newbee.config;

import com.study.maven.newbee.config.entity.IdWorkerProPerties;
import com.study.maven.newbee.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author HLH
 * @version 1.0
 * @email 17703595860@163.com
 */
@Configuration
public class IdWorkerConfig {

    @Autowired
    private IdWorkerProPerties idWorkerProPerties;

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(idWorkerProPerties.getWorkerId(), idWorkerProPerties.getDatacenterId());
    }

}
