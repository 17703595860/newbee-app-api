package com.study.maven.newbee.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 21:47:42
 */
@ApiModel("生成订单参数VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderParamVO implements Serializable {
    private static final long serialVersionUID = -1754478649236271112L;

    @ApiModelProperty("购物车id集合")
    private List<Long> cartItemIds;
    @ApiModelProperty("订单地址id")
    private Long addressId;

}
