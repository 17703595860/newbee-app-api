package com.study.maven.newbee.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/8 15:34:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("更新用户VO")
public class UpdateUserVO {

    @ApiModelProperty("用户昵称")
    private String nickName;        // 用户昵称
    @ApiModelProperty("密码")
    private String password;     // 密码
    @ApiModelProperty("个性签名")
    private String introduceSign;   // 个性签名

}
