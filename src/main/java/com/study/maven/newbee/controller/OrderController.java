package com.study.maven.newbee.controller;

import com.study.maven.newbee.base.controller.ResultGenerator;
import com.study.maven.newbee.config.annotation.TokenToUser;
import com.study.maven.newbee.config.entity.OrderProperties;
import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.service.CartService;
import com.study.maven.newbee.service.OrderService;
import com.study.maven.newbee.vo.OrderParamVO;
import com.study.maven.newbee.vo.OrderVO;
import com.study.maven.newbee.vo.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 20:55:21
 */
@RestController
@RequestMapping("/api/order")
@Api(tags = "订单接口")
public class OrderController implements ResultGenerator {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderProperties orderProperties;
    @Autowired
    private CartService cartService;

    @PostMapping("/generateOrder")
    @ApiOperation("根据购物车id和地址id生成订单")
    public ResponseEntity<Result<?>> generateOrder(@ApiParam("生成订单的数据封装，包括购物车id集合，地址id") @RequestBody OrderParamVO orderParamVO, @TokenToUser @ApiIgnore User user) {
        String orderId = orderService.generateOrder(orderParamVO, user.getUserId());
        return success(orderId);
    }

    @GetMapping("/paySuccess")
    @ApiOperation(value = "模拟支付成功的回调接口", notes = "传参订单号和支付方式")
    public ResponseEntity<Result<?>> paySuccess(
            @ApiParam("订单编号") @RequestParam("orderNo") String orderNo,
            @ApiParam("支付方式") @RequestParam("payType") Integer payType,
            @TokenToUser @ApiIgnore User user
    ) {
        // 如果不在支付类型之内，直接返回错误
        List<Integer> orderPayTypeRange = orderProperties.getPayStatusRange().entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        if (orderPayTypeRange.contains(payType)) {
            return internalServererror("支付类型错误");
        }
        // 模拟现在已经支付成功，这是回调方法处理
        String resultOrderNo = orderService.paySuccess(orderNo, payType, user.getUserId());
        return success(resultOrderNo);
    }

    @GetMapping("{orderNo}")
    @ApiOperation("获取订单详情")
    public ResponseEntity<Result<?>> getOrderDetails(@ApiParam("订单编号") @PathVariable String orderNo, @TokenToUser @ApiIgnore User user) {
        OrderVO orderVO = orderService.getOrderDetails(orderNo, user.getUserId());
        if (orderVO == null) {
            return notFound();
        }
        return success(orderVO);
    }

    @GetMapping
    @ApiOperation("获取订单列表")
    public ResponseEntity<Result<?>> getOrderlist(@TokenToUser @ApiIgnore User user) {
        List<OrderVO> orderVOS = orderService.getOrderlist(user.getUserId());
        if (CollectionUtils.isEmpty(orderVOS)) {
            return notFound();
        }
        return success(orderVOS);
    }

    /*@GetMapping("/pay-status/{payStatus}")
    @ApiOperation("根据订单支付状态查询订单")
    public ResponseEntity<Result<?>> getOrderByPayStatus(@ApiParam("订单支付状态") @PathVariable Integer payStatus, @TokenToUser @ApiIgnore User user) {
        if (!orderProperties.getPayStatusRange().contains(payStatus)) {
            return internalServererror("参数错误");
        }
        return success();
    }*/


}
