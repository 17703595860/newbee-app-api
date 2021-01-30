package com.study.maven.newbee.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:46:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "首页商品VO")
public class IndexGoodsVO implements Serializable {
    private static final long serialVersionUID = -1387499404703421031L;

    private Long goodsId;
    private String goodsName;
    private String goodsCoverImg;
    private String redirectUrl;
    private Integer originalPrice;
    private Integer sellingPrice;

}
