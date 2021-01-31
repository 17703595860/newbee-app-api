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
 * @date : Created in  2021/1/31 16:07:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("购物车参数VO对象")
public class CartParamVO implements Serializable {
    private static final long serialVersionUID = -8272738143350874332L;

    @ApiModelProperty("商品id")
    private Long goodsId;           // 商品id
    @ApiModelProperty("商品数量")
    private Integer goodsCount;     // 商品数量

}
