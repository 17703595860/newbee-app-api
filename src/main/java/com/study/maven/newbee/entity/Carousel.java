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
 * 商品轮播图片表
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:24:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_newbee_mall_carousel")
public class Carousel implements Serializable {
    private static final long serialVersionUID = 1382973986781921806L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carouselId;         // 轮播图id
    private String carouselUrl;         // 轮播图图url
    private String redirectUrl;         // 点击的重定向地址
    private Integer carouselRank;       // 排序字段，越高越前
    private Boolean isDeleted;          // 是否删除
    private Date createTime;        // 创建时间
    private Integer createUser;         // 创建用户
    private Date updateTime;        // 更新时间
    private Integer updateUser;         // 更新用户

}
