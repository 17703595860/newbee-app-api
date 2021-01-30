package com.study.maven.newbee.service;

import com.study.maven.newbee.vo.IndexGoodsVO;
import com.study.maven.newbee.vo.IndexInfoVO;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:55:07
 */
public interface IndexConfigService {

    /**
     * 获取首页展示的所有数据
     * @return IndexInfoVO 首页数据对象
     */
    IndexInfoVO getIndexInfo();

    /**
     * 根据configType获取 指定数量 的首页商品VO集合，供首页使用，按排序字段降序排列
     * @param configType 类型，3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐
     * @return List<IndexGoodsVO> 根绝类型查到的集合
     */
    List<IndexGoodsVO> getIndexGoodsVOListByTypeAndNumDesc(Integer configType, Integer num);

}
