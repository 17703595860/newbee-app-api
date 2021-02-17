package com.study.maven.newbee.vo;

import com.study.maven.newbee.entity.UserAddress;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("用户地址VO")
public class UserAddressVO {

    @ApiModelProperty("收货地址id")
    private Long addressId;             // 收货地址id
    @ApiModelProperty("收货人姓名")
    @NotBlank
    private String userName;            // 收货人姓名
    @ApiModelProperty("收货手机号")
    @NotBlank
    private String userPhone;             // 收货手机号
    @ApiModelProperty("是否默认地址")
    @NotNull
    private Boolean defaultFlag;        // 是否默认地址
    @ApiModelProperty("省")
    @NotBlank
    private String provinceName;        // 省
    @ApiModelProperty("市")
    @NotBlank
    private String cityName;            // 市
    @ApiModelProperty("县")
    @NotBlank
    private String regionName;          // 县
    @ApiModelProperty("详细地址")
    @NotBlank
    private String detailAddress;       // 详细地址


    public static UserAddressVO transform(UserAddress userAddress) {
        UserAddressVO userAddressVO = new UserAddressVO();
        BeanUtils.copyProperties(userAddress, userAddressVO);
        return userAddressVO;
    }
}
