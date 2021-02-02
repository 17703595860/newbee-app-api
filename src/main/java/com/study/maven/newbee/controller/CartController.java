package com.study.maven.newbee.controller;

import com.study.maven.newbee.base.controller.ResultGenerator;
import com.study.maven.newbee.config.annotation.TokenToUser;
import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.service.CartService;
import com.study.maven.newbee.vo.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;
import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/31 15:43:59
 */
@RestController
@RequestMapping("/api/shop-cart")
@Api(tags = "购物车接口")
public class CartController implements ResultGenerator {

    @Autowired
    private CartService cartService;

    @GetMapping
    @ApiOperation("获取整个当前登录的购物车数据")
    public ResponseEntity<Result<?>> getOwnCart(@ApiIgnore @TokenToUser User user) {
        PageResult<CartVO> pageResult = cartService.getOwnCart(user.getUserId(), -1, -1);
        List<CartVO> carts = pageResult.getList();
        if (CollectionUtils.isEmpty(carts)) {
            return notFound();
        }
        return success(carts);
    }

    @GetMapping("/page")
    @ApiOperation("获取整个当前登录的购物车数据")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageSize", required = false, defaultValue = "10", value = "页面长度"),
            @ApiImplicitParam(name = "currentPage", required = false, defaultValue = "1", value = "当前页面")
    })
    public ResponseEntity<Result<?>> getOwnCartByPage(
            @ApiIgnore @TokenToUser User user,
            @RequestParam(name = "pageSize", required = false, defaultValue = "0") Integer pageSize,
            @RequestParam(name = "currentPage", required = false, defaultValue = "0") Integer currentPage)
    {
        PageResult<CartVO> pageResult = cartService.getOwnCart(user.getUserId(), pageSize, currentPage);
        if (CollectionUtils.isEmpty(pageResult.getList())) {
            return notFound();
        }
        return success(pageResult);
    }

    @PostMapping
    @ApiOperation("添加购物车")
    public ResponseEntity<Result<?>> addCart(
            @RequestBody @ApiParam("要添加的购物车数据") CartParamVO cartParamVO,
            @ApiIgnore @TokenToUser User user
    ) {
        cartService.addCart(cartParamVO, user.getUserId());
        return success();
    }

    @PutMapping
    @ApiOperation("修改数量")
    public ResponseEntity<Result<?>> updateCartNum(@ApiParam("要修改的数量封装VO") @RequestBody UpdateCartParamVO updateCartParamVO, @ApiIgnore @TokenToUser User user) {
        cartService.updateCartNum(updateCartParamVO, user.getUserId());
        return success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除购物车数据")
    public ResponseEntity<Result<?>> updateCartNum(@ApiParam("要删除的购物车id") @PathVariable Long id, @ApiIgnore @TokenToUser User user) {
        cartService.deleteCart(id, user.getUserId());
        return success();
    }

    @DeleteMapping("/clear")
    @ApiOperation("清空购物车数据")
    public ResponseEntity<Result<?>> updateCartNum(@ApiIgnore @TokenToUser User user) {
        cartService.clearCart(user.getUserId());
        return success();
    }


    @GetMapping("/cart-sure")
    @ApiOperation(value = "根据购物车id查询购物车明细", notes = "确认订单页面使用")
    @ApiImplicitParam(name = "cartIds", value = "购物车id集合，字符串，用逗号分隔，使用mvc解析")
    public ResponseEntity<Result<?>> getCartVOByIds(Long [] cartIds, @TokenToUser @ApiIgnore User user) {
        List<Long> cartIdList = Arrays.asList(cartIds);
        if (CollectionUtils.isEmpty(cartIdList)) {
            return internalServererror("参数错误");
        }
        List<CartVO> cartVOS = cartService.getCartVOListByCartIds(cartIdList, user.getUserId());
        if (CollectionUtils.isEmpty(cartVOS)) {
            return notFound();
        }
        return success(cartVOS);
    }

}
