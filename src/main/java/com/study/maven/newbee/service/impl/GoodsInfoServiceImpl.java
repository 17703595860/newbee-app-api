package com.study.maven.newbee.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.maven.newbee.config.entity.Constants;
import com.study.maven.newbee.entity.GoodsInfo;
import com.study.maven.newbee.mapper.GoodsInfoMapper;
import com.study.maven.newbee.service.GoodsInfoService;
import com.study.maven.newbee.vo.GoodsDetailVO;
import com.study.maven.newbee.vo.IndexGoodsVO;
import com.study.maven.newbee.vo.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 19:40:20
 */
@Service("goodsInfoService")
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private Constants constants;

    @Override
    public PageResult<IndexGoodsVO> searchGoods(String keyword, Long categoryId, Integer pageSize, Integer currentPage, String orderBy, Boolean order) {
       // 分页参数判断
        pageSize = pageSize == null || pageSize < 1 ? constants.getPageSize() : pageSize;
        currentPage = currentPage == null || currentPage < 1 ? constants.getCurrentPage() : currentPage;
        // 查询 分页
        PageHelper.startPage(currentPage, pageSize);
        List<IndexGoodsVO> goodsVOList = goodsInfoMapper.selectGoodsByPage(keyword, categoryId, orderBy, order);
        PageInfo<IndexGoodsVO> pageInfo = new PageInfo<>(goodsVOList);
        return new PageResult<>(pageInfo);
    }

    @Override
    public GoodsDetailVO getGoodsDetailVOById(Long id) {
        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(id);
        if (goodsInfo == null) return null;
        return GoodsDetailVO.transform(goodsInfo);
    }
}
