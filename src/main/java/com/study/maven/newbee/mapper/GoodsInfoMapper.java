package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.GoodsInfo;
import com.study.maven.newbee.vo.IndexGoodsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:36:02
 */
@Repository
public interface GoodsInfoMapper extends Mapper<GoodsInfo> {


    /**
     * 根据条件查询商品
     * @param keyword 查询条件 商品名称
     * @param categoryId 分类id
     * @param orderBy  排序条件 new 新品  price 价格  其他+默认 id
     * @param order  排序方式 true，升序，false，降序
     * @return 查询到的数据
     */
    List<IndexGoodsVO> selectGoodsByPage(@Param("keyword") String keyword, @Param("categoryId") Long categoryId,
                                         @Param("orderBy") String orderBy, @Param("order") Boolean order);
}
