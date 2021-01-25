package com.study.maven.newbee.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/25 22:52:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("登录传参对象")
public class UserLoginParam implements Serializable {

    private static final long serialVersionUID = -7467158830861216351L;

    @ApiModelProperty("登录名")
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;

}
