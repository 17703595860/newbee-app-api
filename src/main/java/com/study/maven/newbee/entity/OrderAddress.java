package com.study.maven.newbee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 订单对应的地址表，快照
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_order_address")
public class OrderAddress implements Serializable {
    private static final long serialVersionUID = -3999494652660435921L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;           // 订单id
    private String userName;        // 收货人姓名
    private String userPhone;       // 收货人手脚
    private String provinceName;    // 省
    private String cityName;        // 市
    private String regionName;      // 县
    private String detailAddress;   // 详细地址

}
