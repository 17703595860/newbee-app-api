package com.study.maven.newbee.vo;

import com.study.maven.newbee.entity.OrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/4 14:00:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("订单商品VO")
public class OrderItemVO implements Serializable {
    private static final long serialVersionUID = 6966474294924864951L;

    @ApiModelProperty("订单项id")
    private Long orderItemId;       // 订单项id
    @ApiModelProperty("订单id")
    private Long orderId;           // 订单id
    @ApiModelProperty("商品id")
    private Long goodsId;           // 商品id
    @ApiModelProperty("商品名称")
    private String goodsName;       // 商品名称（快照）
    @ApiModelProperty("商品主图")
    private String goodsCoverImg;   // 商品主图（快照）
    @ApiModelProperty("商品售价")
    private Integer sellingPrice;   // 商品售价（快照）
    @ApiModelProperty("商品数量")
    private Integer goodsCount;     // 商品数量（快照）

    public static OrderItemVO transform(OrderItem orderItem) {
        OrderItemVO orderItemVO = new OrderItemVO();
        BeanUtils.copyProperties(orderItem, orderItemVO);
        return orderItemVO;
    }
}
