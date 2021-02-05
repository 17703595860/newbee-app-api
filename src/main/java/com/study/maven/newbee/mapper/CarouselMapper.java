package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.Carousel;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 10:35:07
 */
@Repository
public interface CarouselMapper extends Mapper<Carousel> {

    /**
     * 查询所有有效的轮播图记录，通过carouselRank排序
     * @return  List<Carousel> 查找到的记录
     */
    List<Carousel> selectAllOrderByCarouselRank();
}
