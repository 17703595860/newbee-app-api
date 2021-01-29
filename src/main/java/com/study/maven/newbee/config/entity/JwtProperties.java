package com.study.maven.newbee.config.entity;

import com.study.maven.newbee.utils.RsaUtils;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/26 22:27:28
 */
@Slf4j
@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties implements Serializable {
    private static final long serialVersionUID = 1908377128318828714L;

    private String secret;
    private String pubKeyPath;
    private String priKeyPath;
    private Integer expire;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void postConstruct() {
        try {
            File pubKeyFile = new File(pubKeyPath);
            File priKeyFile = new File(priKeyPath);
            // 如果文件不存在，生成公钥和私钥
            if (!pubKeyFile.exists() || !priKeyFile.exists()){
                RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
            }
            // 获取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
        } catch (Exception e) {
            log.error("初始化公钥和私钥失败！", e);
            throw new RuntimeException();
        }
    }

}
