package com.study.maven.newbee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单实体类
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 2341898777226119388L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long orderId;           // 订单主表id
    private String orderNo;         // 订单编号
    private Long userId;            // 用户id
    private Integer totalPrice;     // 总价格
    private Integer payStatus;      // 支付状态，0未支付，1已支付，2支付失败
    private Integer payType;        // 支付类型，0无，1支付宝支付，2微信支付
    private Date payTime;           // 支付时间
    private Integer orderStatus;    // 订单状态，0待支付，1已支付，2配货完成，3出库成功，4交易成功，-1手动关闭，-2超时关闭，-3商家关闭
    private String extraInfo;       // 订单body
    private Boolean isDeleted;       // 是否删除
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间

}
