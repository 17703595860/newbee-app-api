package com.study.maven.newbee.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 21:47:42
 */
@ApiModel("修改订单状态VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderStatusVO implements Serializable {
    private static final long serialVersionUID = -1754478649236271112L;

    @ApiModelProperty("订单编号")
    @NotBlank
    private String orderNo;
    @ApiModelProperty("订单状态")
    @NotNull
    private Integer status;

}
