package com.study.maven.newbee.vo;

import com.study.maven.newbee.entity.GoodsInfo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/31 11:09:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("商品详情VO")
public class GoodsDetailVO implements Serializable {
    private static final long serialVersionUID = 433035110259151928L;

    private Long goodsId;           // 商品id
    private String goodsName;       // 商品名称
    private String goodsIntro;      // 商品简介
    private String goodsCoverImg;   // 商品主图
    private String[] goodsCarousels;   // 商品图片集合，用逗号分割
    private String goodsDetailContent;  // 商品详情
    private Integer originalPrice;   // 商品价格
    private Integer sellingPrice;   // 商品实际价格
    private Integer stockNum;       // 库存数量
    private String tag;             // 商品标签


    public static GoodsDetailVO transform(GoodsInfo goodsInfo) {
        GoodsDetailVO goodsDetailVO = builder()
                .goodsCarousels(StringUtils.split(goodsInfo.getGoodsCarousel(), ","))
                .build();
        BeanUtils.copyProperties(goodsInfo, goodsDetailVO);
        return goodsDetailVO;
    }
}
