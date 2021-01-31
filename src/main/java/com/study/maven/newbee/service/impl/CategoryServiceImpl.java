package com.study.maven.newbee.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.study.maven.newbee.mapper.CategoryMapper;
import com.study.maven.newbee.service.CategoryService;
import com.study.maven.newbee.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 17:54:55
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> getCategories() {
        List<CategoryVO> categoryVOList = categoryMapper.selectAllCategory();
        return categoryVOList;
    }
}
