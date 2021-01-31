package com.study.maven.newbee.controller;

import com.study.maven.newbee.base.controller.ResultGenerator;
import com.study.maven.newbee.config.annotation.CurrentPage;
import com.study.maven.newbee.config.annotation.PageSize;
import com.study.maven.newbee.config.entity.Constants;
import com.study.maven.newbee.service.GoodsInfoService;
import com.study.maven.newbee.vo.GoodsDetailVO;
import com.study.maven.newbee.vo.IndexGoodsVO;
import com.study.maven.newbee.vo.PageResult;
import com.study.maven.newbee.vo.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 19:41:15
 */
@RestController
@RequestMapping("/api/goods-info")
@Api(tags = "商品接口")
public class GoodsInfoController implements ResultGenerator {

    @Autowired
    private Constants constants;
    @Autowired
    private GoodsInfoService goodsInfoService;

    @GetMapping
    @ApiOperation(value = "商品搜索接口，返回分页对象", httpMethod = "GET")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "keyword", required = false, value = "查询参数，商品名称"),
            @ApiImplicitParam(name = "categoryId", required = false, value = "三级分类id"),
            @ApiImplicitParam(name = "pageSize", required = false, defaultValue = "10", value = "每页条数"),
            @ApiImplicitParam(name = "currentPage", required = false, defaultValue = "1", value = "当前页码"),
            @ApiImplicitParam(name = "orderBy", required = false, value = "排序字段,default默认，new新品排序，price，价格排序"),
            @ApiImplicitParam(name = "order", required = false, defaultValue = "false", value = "排序方式，true升序，false降序，默认降序"),
    })
    public ResponseEntity<Result<?>> searchGoods(
            String keyword, Long categoryId, Integer pageSize, Integer currentPage, String orderBy,
            @RequestParam(value = "order", required = false, defaultValue = "false") Boolean order
    ) {
        PageResult<IndexGoodsVO> pageResult = goodsInfoService.searchGoods(keyword, categoryId, pageSize, currentPage, orderBy, order);
        if (CollectionUtils.isEmpty(pageResult.getList())) {
            return notFound();
        }
        return success(pageResult);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation("商品详情接口")
    public ResponseEntity<Result<?>> getGoodsDetail(@PathVariable @ApiParam("商品id") Long id) {
        GoodsDetailVO goodsDetailVO = goodsInfoService.getGoodsDetailVOById(id);
        if (goodsDetailVO == null) {
            return notFound();
        }
        return success(goodsDetailVO);
    }

}
