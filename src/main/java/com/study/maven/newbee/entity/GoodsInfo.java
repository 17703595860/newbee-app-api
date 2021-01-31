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
 * 商品信息表
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:13:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_goods_info")
public class GoodsInfo implements Serializable {
    private static final long serialVersionUID = -9212076630243159452L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodsId;           // 商品id
    private String goodsName;       // 商品名称
    private String goodsIntro;      // 商品简介
    private Long goodsCategoryId;   // 商品分类id
    private String goodsCoverImg;   // 商品主图
    private String goodsCarousel;   // 商品图片集合，用逗号分割
    private String goodsDetailContent;  // 商品详情
    private Integer originalPrice;   // 商品价格
    private Integer sellingPrice;   // 商品实际价格
    private Integer stockNum;       // 库存数量
    private String tag;             // 商品标签
    private Boolean goodsSellStatus;    // 商品上架状态，1上架，0下架
    private Integer createUser;         // 创建人
    private Date createTime;            // 创建时间
    private Integer updateUser;         // 更新人
    private Date updateTime;         // 更新时间

}
