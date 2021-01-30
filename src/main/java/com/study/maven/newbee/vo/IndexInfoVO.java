package com.study.maven.newbee.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:51:09
 */@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "首页信息VO")
public class IndexInfoVO implements Serializable {
    private static final long serialVersionUID = -9047398230547903844L;

    private List<IndexCarouseVO> carouses;
    private List<IndexGoodsVO> hotGoods;
    private List<IndexGoodsVO> newGoods;
    private List<IndexGoodsVO> recommendGoods;

 }
