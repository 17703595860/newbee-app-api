package com.study.maven.newbee.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.maven.newbee.base.BaseTest;
import com.study.maven.newbee.entity.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 9:45:31
 */
public class CategoryMapperTest extends BaseTest {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void findAll() throws JsonProcessingException {
        Category category = categoryMapper.selectByPrimaryKey(15);
        System.out.println(category);
        System.out.println(objectMapper.writeValueAsString(category));
        System.out.println(objectMapper.readValue("{\"categoryId\":15,\"categoryLevel\":1,\"parentId\":0,\"categoryName\":\"家电 数码 手机\",\"categoryRank\":100,\"isDeleted\":false,\"createTime\":\"2019-09-11 10:45:40\",\"createUser\":0,\"updateTime\":\"2019-11-20 15:18:13\",\"updateUser\":0}", Category.class));
    }

}