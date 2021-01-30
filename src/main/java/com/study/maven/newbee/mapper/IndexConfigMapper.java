package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.IndexConfig;
import com.study.maven.newbee.vo.IndexGoodsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:36:48
 */
@Repository
public interface IndexConfigMapper extends Mapper<IndexConfig> {

    /**
     * 根据configType获取 指定数量 首页商品VO集合，供首页使用，按排序字段降序排列，有效的
     * @param configType 类型，3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐
     * @return List<IndexGoodsVO> 根绝类型查到的集合
     */
    List<IndexGoodsVO> selectIndexGoodsVOListByTypeAndNumDesc(@Param("configType") Integer configType, @Param("num") Integer num);
}
