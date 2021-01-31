package com.study.maven.newbee.service;

import com.study.maven.newbee.vo.IndexGoodsVO;
import com.study.maven.newbee.vo.PageResult;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 19:40:09
 */
public interface GoodsInfoService {

    /**
     * 根据条件查询商品信息，分页，排序
     * @param keyword 查询条件，这是商品名称
     * @param categoryId 三级分类id
     * @param pageSize 每页尺寸
     * @param currentPage 当前选择页面
     * @param orderBy 排序字段
     * @param order 排序方式
     * @return 查询的结果
     */
    PageResult<IndexGoodsVO> searchGoods(String keyword, Long categoryId, Integer pageSize, Integer currentPage, String orderBy, Boolean order);
}
