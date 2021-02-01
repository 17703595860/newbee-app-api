package com.study.maven.newbee.controller;

import com.study.maven.newbee.base.controller.ResultGenerator;
import com.study.maven.newbee.config.annotation.TokenToUser;
import com.study.maven.newbee.entity.Order;
import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.mapper.OrderMapper;
import com.study.maven.newbee.service.OrderService;
import com.study.maven.newbee.vo.OrderParamVO;
import com.study.maven.newbee.vo.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 20:55:21
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单接口")
public class OrderController implements ResultGenerator {

    @Autowired
    private OrderService orderService;

    @PostMapping("/generateOrder")
    @ApiOperation("根据购物车id和地址id生成订单")
    public ResponseEntity<Result<?>> generateOrder(@ApiParam("购物车编号") @RequestBody OrderParamVO orderParamVO, @TokenToUser @ApiIgnore User user) {
        Long orderId = orderService.generateOrder(orderParamVO, user.getUserId());
        return success(orderId);
    }

    @GetMapping("/pay-status/{payStatus}")
    @ApiOperation("根据订单支付状态查询订单")
    public ResponseEntity<Result<?>> getOrderByPayStatus(@ApiParam("订单支付状态") @PathVariable Integer payStatus, @TokenToUser @ApiIgnore User user) {

        return success();
    }


}
