package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.Category;
import com.study.maven.newbee.vo.CategoryVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 9:44:44
 */
@Repository
public interface CategoryMapper extends Mapper<Category> {

    /**
     * 获取三级分类的id(有效，排序)
     * @return 获取三级分类的id
     */
    List<CategoryVO> selectAllCategory();
}
