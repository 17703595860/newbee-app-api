package com.study.maven.newbee.controller;

import com.study.maven.newbee.base.controller.ResultGenerator;
import com.study.maven.newbee.config.annotation.TokenToUser;
import com.study.maven.newbee.config.entity.OrderProperties;
import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.service.CartService;
import com.study.maven.newbee.service.OrderService;
import com.study.maven.newbee.vo.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
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
        String orderNo = orderService.generateOrder(orderParamVO, user.getUserId());
        return success(orderNo);
    }

    @GetMapping("/paySuccess")
    @ApiOperation(value = "模拟支付成功的回调接口", notes = "传参订单号和支付方式")
    public ResponseEntity<Result<?>> paySuccess(
            @ApiParam("订单编号") @RequestParam("orderNo") String orderNo,
            @ApiParam("支付方式") @RequestParam("payType") Integer payType,
            @TokenToUser @ApiIgnore User user
    ) {
        // 如果不在支付类型之内，直接返回错误
        List<Integer> orderPayTypeRange = orderProperties.getPayTypeRange().entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        if (!orderPayTypeRange.contains(payType)) {
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

    @GetMapping("/page")
    @ApiOperation("获取订单列表（分页）")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageSize", required = false, defaultValue = "10", value = "每页条数"),
            @ApiImplicitParam(name = "currentPage", required = false, defaultValue = "1", value = "当前页码"),
            @ApiImplicitParam(name = "status", required = false, value = "订单状态")
    })
    public ResponseEntity<Result<?>> getOrderlistPage(Integer pageSize, Integer currentPage, Integer status, @TokenToUser @ApiIgnore User user) {
        List<Integer> statusRange = orderProperties.getOrderStatusRange().entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        if (status != null && status != -100 && !statusRange.contains(status)){
            return internalServererror("状态码错误");
        }
        PageResult<OrderVO> orderVOPage = orderService.getOrderlistPage(pageSize, currentPage, status, user.getUserId());
        if (CollectionUtils.isEmpty(orderVOPage.getList())) {
            return notFound();
        }
        return success(orderVOPage);
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

    @PutMapping("/updateOrderStatus")
    @ApiOperation("修改订单状态")
    public ResponseEntity<Result<?>> updateOrderStatus(@ApiParam("修改订单状态VO") @Valid @RequestBody OrderStatusVO orderStatusVO, @TokenToUser @ApiIgnore User user) {
        List<Integer> statusRange = orderProperties.getOrderStatusRange().entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
        Integer status = orderStatusVO.getStatus();
        if (!statusRange.contains(status)){
            return internalServererror("状态码错误");
        }
        orderService.updateOrderStatus(orderStatusVO, user.getUserId());
        OrderVO orderVO = orderService.getOrderDetails(orderStatusVO.getOrderNo(), user.getUserId());
        return success(orderVO);
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
