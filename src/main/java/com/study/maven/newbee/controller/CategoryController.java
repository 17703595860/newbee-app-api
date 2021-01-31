package com.study.maven.newbee.controller;

import com.study.maven.newbee.base.controller.ResultGenerator;
import com.study.maven.newbee.service.CategoryService;
import com.study.maven.newbee.vo.CategoryVO;
import com.study.maven.newbee.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 17:55:45
 */
@RestController
@RequestMapping("/api/category")
@Api(tags = "分类接口")
public class CategoryController implements ResultGenerator {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    @ApiOperation("获取三级分类的信息")
    public ResponseEntity<Result<?>> getCategories() {
        List<CategoryVO> categories = categoryService.getCategories();
        if (CollectionUtils.isEmpty(categories)) {
            return notFound("无分类数据");
        }
        return success(categories);
    }

}
