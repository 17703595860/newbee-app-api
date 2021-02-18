package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:32
 */
@Repository
public interface OrderMapper extends Mapper<Order> {

    /**
     * 根据订单编号查询订单
     * @param orderNo 订单编号
     * @param userId 当前登录用户的id
     * @return 订单
     */
    Order selectByOrderNoAndUserId(@Param("orderNo") String orderNo, @Param("userId") Long userId);

    /**
     * 根据用户id查询订单列表
     * @param userId 用户id
     * @return 订单列表
     */
    List<Order> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据用户id和订单状态查询订单信息
     * @param userId 用户id
     * @param status 订单状态
     * @return 订单列表数据
     */
    List<Order> selectByUserIdAndOrderStatus(@Param("userId") Long userId, @Param("status") Integer status);

    /**
     * 根据用户id，订单编号查询订单
     * @param userId 用户id
     * @param orderNo 订单编号
     * @return 订单信息
     */
    Order selectByUserIdAndOrderNo(@Param("userId") Long userId, @Param("orderNo") String orderNo);
}
