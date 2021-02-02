package com.study.maven.newbee.service.impl;

import com.study.maven.newbee.config.entity.Constants;
import com.study.maven.newbee.entity.Carousel;
import com.study.maven.newbee.mapper.CarouselMapper;
import com.study.maven.newbee.mapper.IndexConfigMapper;
import com.study.maven.newbee.service.IndexConfigService;
import com.study.maven.newbee.vo.IndexCarouseVO;
import com.study.maven.newbee.vo.IndexGoodsVO;
import com.study.maven.newbee.vo.IndexInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:55:17
 */
@Service("indexConfigService")
@Transactional
public class IndexConfigServiceImpl implements IndexConfigService {

    @Autowired
    private CarouselMapper carouselMapper;
    @Autowired
    private IndexConfigMapper indexConfigMapper;
    @Autowired
    private Constants constants;

    @Override
    public IndexInfoVO getIndexInfo() {
        IndexInfoVO indexInfoVO = new IndexInfoVO();
        // 轮播图(有效的)
        Example example = new Example(Carousel.class);
        example.orderBy("carouselRank").desc();
        example.createCriteria().andEqualTo("isDeleted", false);
        List<Carousel> carousels = carouselMapper.selectByExample(example);
        List<IndexCarouseVO> indexCarouseVOS = carousels.stream().map(IndexCarouseVO::transform).collect(Collectors.toList());
        indexInfoVO.setCarouses(indexCarouseVOS);
        // 热门商品
        List<IndexGoodsVO> hotGoods = getIndexGoodsVOListByTypeAndNumDesc(3, constants.getIndexNum());
        indexInfoVO.setHotGoods(hotGoods);
        // 新品上线
        List<IndexGoodsVO> newGoods = getIndexGoodsVOListByTypeAndNumDesc(4, constants.getIndexNum());
        indexInfoVO.setNewGoods(newGoods);
        // 推荐商品
        List<IndexGoodsVO> recommendGoods = getIndexGoodsVOListByTypeAndNumDesc(5, constants.getIndexNum());
        indexInfoVO.setRecommendGoods(recommendGoods);
        return indexInfoVO;
    }

    @Override
    public List<IndexGoodsVO> getIndexGoodsVOListByTypeAndNumDesc(Integer configType, Integer num) {
        return indexConfigMapper.selectIndexGoodsVOListByTypeAndNumDesc(configType, num);
    }

}
