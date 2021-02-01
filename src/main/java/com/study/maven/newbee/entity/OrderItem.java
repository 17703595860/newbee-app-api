package com.study.maven.newbee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_newbee_mall_order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 3205340337910331168L;

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long orderItemId;       // 订单项id
    private Long orderId;           // 订单id
    private Long goodsId;           // 商品id
    private String goodsName;       // 商品名称（快照）
    private String goodsCoverImg;   // 商品主图（快照）
    private Integer sellingPrice;   // 商品售价（快照）
    private Integer goodsCount;     // 商品数量（快照）
    private Date createTime;        // 创建时间

}
