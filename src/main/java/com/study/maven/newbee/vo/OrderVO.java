package com.study.maven.newbee.vo;

import com.study.maven.newbee.config.entity.OrderProperties;
import com.study.maven.newbee.entity.Order;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/4 13:49:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("订单详情vo")
public class OrderVO implements Serializable {
    private static final long serialVersionUID = -6077079015305956037L;

    @ApiModelProperty("订单编号")
    private String orderNo;         // 订单编号
    @ApiModelProperty("用户id")
    private Long userId;            // 用户id
    @ApiModelProperty("总价格")
    private Integer totalPrice;     // 总价格
    @ApiModelProperty("支付状态")
    private Integer payStatus;      // 支付状态，0未支付，1已支付，2支付失败
    @ApiModelProperty("支付类型")
    private Integer payType;        // 支付类型，0无，1支付宝支付，2微信支付
    @ApiModelProperty("支付时间")
    private Date payTime;           // 支付时间
    @ApiModelProperty("订单状态")
    private Integer orderStatus;    // 订单状态，0待支付，1已支付，2配货完成，3出库成功，4交易成功，-1手动关闭，-2超时关闭，-3商家关闭
    @ApiModelProperty("订单body")
    private String extraInfo;       // 订单body
    @ApiModelProperty("是否删除")
    private Boolean isDeleted;       // 是否删除
    @ApiModelProperty("创建时间")
    private Date createTime;        // 创建时间
    @ApiModelProperty("支付状态字符串形式")
    private String payStatusString;      // 支付状态，0未支付，1已支付，2支付失败
    @ApiModelProperty("支付类型字符串形式")
    private String payTypeString;        // 支付类型，0无，1支付宝支付，2微信支付
    @ApiModelProperty("订单状态字符串形式")
    private String orderStatusString;    // 订单状态，0待支付，1已支付，2配货完成，3出库成功，4交易成功，-1手动关闭，-2超时关闭，-3商家关闭

    @ApiModelProperty("订单项集合")
    private List<OrderItemVO> orderItems;

    public static OrderVO transform(Order order, OrderProperties orderProperties) {
        Map<Integer, String> payTypeMap = orderProperties.getPayTypeRange();
        Map<Integer, String> payStatusMap = orderProperties.getPayStatusRange();
        Map<Integer, String> orderStatusMap = orderProperties.getOrderStatusRange();
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        orderVO.setPayTypeString(payTypeMap.get(order.getPayType()));
        orderVO.setPayStatusString(payStatusMap.get(order.getPayStatus()));
        orderVO.setOrderStatusString(orderStatusMap.get(order.getOrderStatus()));
        return orderVO;
    }
}
