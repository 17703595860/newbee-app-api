package com.study.maven.newbee.config.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/2 9:25
 */
@Component
@Data
@ConfigurationProperties(prefix = "order")
public class OrderProperties implements Serializable {
    private static final long serialVersionUID = 4825441660446223438L;

    private Map<Integer, String> payStatusRange;       // 支付状态可选值
    private Map<Integer, String> payTypeRange;         // 支付类型可选值
    private Map<Integer, String> orderStatusRange;     // 订单状态可选值

}
