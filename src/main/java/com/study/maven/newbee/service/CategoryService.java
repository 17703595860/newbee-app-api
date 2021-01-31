package com.study.maven.newbee.service;

import com.study.maven.newbee.vo.CategoryVO;

import java.util.List;

/**
 * 三级分类Service
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 17:54:32
 */
public interface CategoryService {

    /**
     * 获取三级分类的id(有效，排序)
     * @return 获取三级分类的id
     */
    List<CategoryVO> getCategories();
}
