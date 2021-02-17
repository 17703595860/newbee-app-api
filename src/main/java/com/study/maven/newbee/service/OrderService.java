package com.study.maven.newbee.service;

import com.study.maven.newbee.vo.OrderParamVO;
import com.study.maven.newbee.vo.OrderStatusVO;
import com.study.maven.newbee.vo.OrderVO;
import com.study.maven.newbee.vo.PageResult;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:34
 */
public interface OrderService {

    /**
     * 根据购物车id和地址id生成订单
     * @param orderParamVO 封装的数据对象
     * @param userId 当前登录的用户id
     * @return 生成订单的编号
     */
    String generateOrder(OrderParamVO orderParamVO, Long userId);

    /**
     * 模拟支付成功，根据订单号和支付类型修改订单状态
     * @param orderNo 订单标号
     * @param payType 支付类型
     * @param userId 当前登录的用户id
     * @return 如果成功，返回订单编号，如果失败，抛出自定义异常，全局处理
     */
    String paySuccess(String orderNo, Integer payType, Long userId);

    /**
     * 根据订单编号和用户id查询订单详情
     * @param orderNo 订单编号
     * @param userId 用户id
     * @return 订单VO
     */
    OrderVO getOrderDetails(String orderNo, Long userId);

    /**
     * 根据用户id查询订单列表
     * @param userId 用户id
     * @return 订单列表id
     */
    List<OrderVO> getOrderlist(Long userId);

    /**
     * 根据用户id, 状态码，分页对象，查询订单列表
     * @param pageSize 页面长度
     * @param currentPage 当前页码
     * @param status 订单状态
     * @param userId 用户id
     * @return 查询到的订单数据
     */
    PageResult<OrderVO> getOrderlistPage(Integer pageSize, Integer currentPage, Integer status, Long userId);

    /**
     * 根据用户id和订单编号，订单状态
     * @param orderStatusVO 订单编号和订单状态封装对象
     * @param userId 用户id
     */
    void updateOrderStatus(OrderStatusVO orderStatusVO, Long userId);
}
