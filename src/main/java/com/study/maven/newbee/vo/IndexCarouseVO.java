package com.study.maven.newbee.vo;

import com.study.maven.newbee.entity.Carousel;
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
 * @date : Created in  2021/1/30 10:43:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "首页轮播图VO")
public class IndexCarouseVO implements Serializable {
    private static final long serialVersionUID = 4154367118055620044L;

    @ApiModelProperty("轮播图图片地址")
    private String carouselUrl;
    @ApiModelProperty("点击跳转的地址")
    private String redirectUrl;

    public static IndexCarouseVO transform(Carousel carousel) {
        return builder()
                .carouselUrl(carousel.getCarouselUrl())
                .redirectUrl(carousel.getRedirectUrl())
                .build();
    }
}
