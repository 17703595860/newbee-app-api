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
import java.util.Date;

/**
 * 购物车实体类，一个用户购物车只能存放五件商品，每件商品不能超多当前库存
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/31 11:50:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_shopping_cart_item")
public class Cart implements Serializable {
    private static final long serialVersionUID = 5481193173963457240L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;            // 购物车id
    private Long userId;            // 用户id
    private Long goodsId;           // 商品id
    private Integer goodsCount;     // 商品数量
    private Boolean isDeleted;      // 是否删除
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间

}
