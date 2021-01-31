package com.study.maven.newbee.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/31 15:38:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("购物车VO对象")
public class CartVO implements Serializable {
    private static final long serialVersionUID = -1063777801986706565L;

    @ApiModelProperty("购物车id")
    private Long cartItemId;            // 购物车id
    @ApiModelProperty("用户id")
    private Long userId;            // 用户id
    @ApiModelProperty("商品id")
    private Long goodsId;           // 商品id
    @ApiModelProperty("商品数量")
    private Integer goodsCount;     // 商品数量


    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("商品图片")
    private String goodsCoverImg;
    @ApiModelProperty("商品价格")
    private Integer originalPrice;   // 商品价格
    @ApiModelProperty("商品实际售价")
    private Integer sellingPrice;
    @ApiModelProperty("上架状态")
    private Boolean goodsSellStatus;    // 商品上架状态，1上架，0下架

}
