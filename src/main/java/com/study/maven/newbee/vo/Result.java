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
 * @date : Created in  2021/1/25 23:00:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "统一返回结果对象")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -5303708196097223305L;

    @Builder.Default
    @ApiModelProperty("返回状态码")
    private Integer resultCode = 200;
    @ApiModelProperty("返回提示信息")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;

}
