package com.study.maven.newbee.service.impl;

import com.study.maven.newbee.config.entity.OrderProperties;
import com.study.maven.newbee.entity.*;
import com.study.maven.newbee.exception.SystemException;
import com.study.maven.newbee.mapper.*;
import com.study.maven.newbee.service.OrderService;
import com.study.maven.newbee.utils.IdWorker;
import com.study.maven.newbee.vo.OrderItemVO;
import com.study.maven.newbee.vo.OrderParamVO;
import com.study.maven.newbee.vo.OrderVO;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:34
 */
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderAddressMapper orderAddressMapper;
    @Autowired
    private OrderProperties orderProperties;

    @Override
    public String generateOrder(OrderParamVO orderParamVO, Long userId) {
        DateTime now = DateTime.now();
        List<Long> cartItemIds = orderParamVO.getCartItemIds();
        Long addressId = orderParamVO.getAddressId();
        if (CollectionUtils.isEmpty(cartItemIds) || addressId == null) {
            throw new SystemException("参数错误");
        }
        // 判断地址
        UserAddress userAddress = userAddressMapper.selectOneByUserIdAndAddressId(userId, addressId);
        if (userAddress == null || userAddress.getIsDeleted()) {
            throw new SystemException("地址不存在，或者已经被删除");
        }
        OrderAddress orderAddress = new OrderAddress();
        BeanUtils.copyProperties(userAddress, orderAddress);
        List<OrderItem> orderItems = new ArrayList<>();
        Integer totalPrice = 0;
        for (Long cartItemId : cartItemIds) {
            Cart cart = cartMapper.selectByPrimaryKey(cartItemId);
            if (cart == null || cart.getIsDeleted()) {
                throw new SystemException("购物车数据不存在");
            }
            GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(cart.getGoodsId());
            if (goodsInfo == null || !goodsInfo.getGoodsSellStatus()) {
                throw new SystemException("商品不存在，或者已经下架");
            }
            if (goodsInfo.getStockNum() < cart.getGoodsCount()) {
                throw new SystemException("商品库存不足");
            }
            goodsInfo.setStockNum(goodsInfo.getStockNum() - cart.getGoodsCount());
            int flag = goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
            if (flag < 1) throw new SystemException("系统错误");
            OrderItem orderItem = new OrderItem();
            BeanUtils.copyProperties(goodsInfo, orderItem);
            orderItem.setCreateTime(now.toDate());
            orderItem.setGoodsCount(cart.getGoodsCount());
            orderItems.add(orderItem);
            totalPrice += orderItem.getGoodsCount() * orderItem.getSellingPrice();
        }
        // 保存订单
        Order order = new Order(null, idWorker.nextId(), userId, totalPrice, 0, 0, null, 0, null, false, now.toDate(), now.toDate());
        int flag = orderMapper.insertSelective(order);
        if (flag < 1) throw new SystemException("系统错误");
        // 保存订单信息
        orderItems.forEach(e -> {
            e.setOrderId(order.getOrderId());
            int flag1 = orderItemMapper.insertSelective(e);
            if (flag1 < 1) throw new SystemException("系统错误");
        });
        // 保存订单地址
        orderAddress.setOrderId(order.getOrderId());
        flag = orderAddressMapper.insertSelective(orderAddress);
        if (flag < 1) throw new SystemException("系统错误");
        // 删除
        flag = cartMapper.deleteByIdList(cartItemIds);
        if (flag < 1) throw new SystemException("系统错误");
        return order.getOrderNo();
    }

    @Override
    public String paySuccess(String orderNo, Integer payType, Long userId) {
        Order order = orderMapper.selectByOrderNoAndUserId(orderNo, userId);
        if (order == null || order.getIsDeleted()) {
            throw new SystemException("订单编号不存在");
        }
        if (order.getPayStatus() != null && order.getPayStatus() != 0) {
            throw new SystemException("订单状态异常，不可支付");
        }
        // 设置支付成功信息
        DateTime now = DateTime.now();
        order.setPayStatus(1);
        order.setPayType(payType);
        order.setPayTime(now.toDate());
        order.setOrderStatus(1);
        // 重新放回数据
        order.setUpdateTime(now.toDate());
        int flag = orderMapper.updateByPrimaryKeySelective(order);
        if (flag < 1) {
            // 失败
            order.setPayStatus(2);
            order.setOrderStatus(-2);
            orderMapper.updateByPrimaryKeySelective(order);
            throw new SystemException("支付失败");
        }
        return order.getOrderNo();
    }

    @Override
    public OrderVO getOrderDetails(String orderNo, Long userId) {
        // 查询订单和订单项信息
        Order order = orderMapper.selectByOrderNoAndUserId(orderNo, userId);
        if (order == null) {
            throw new SystemException("订单编号错误");
        }
        List<OrderItem> orderItems = orderItemMapper.selectByOrderId(order.getOrderId());
        if (CollectionUtils.isEmpty(orderItems)) {
            throw new SystemException("订单信息错误");
        }
        // 装换vo
        OrderVO orderVO = OrderVO.transform(order, orderProperties);
        List<OrderItemVO> orderItemVOS = orderItems.stream().map(OrderItemVO::transform).collect(Collectors.toList());
        orderVO.setOrderItems(orderItemVOS);
        // 返回
        return orderVO;
    }

    @Override
    public List<OrderVO> getOrderlist(Long userId) {
        List<Order> orders = orderMapper.selectByUserId(userId);
        List<Long> orderIds = orders.stream().map(Order::getOrderId).collect(Collectors.toList());
        List<OrderItem> orderItems = orderItemMapper.selectByOrderIds(orderIds);
        List<OrderVO> orderVOS = orders.stream().map(order -> {
            OrderVO orderVO = OrderVO.transform(order, orderProperties);
            List<OrderItemVO> orderItemVOS = orderItems.stream().filter(orderItem -> order.getOrderId().equals(orderItem.getOrderId())).map(OrderItemVO::transform).collect(Collectors.toList());
            orderVO.setOrderItems(orderItemVOS);
            return orderVO;
        }).collect(Collectors.toList());
        return orderVOS;
    }
}
