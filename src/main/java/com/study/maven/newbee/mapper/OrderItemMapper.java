package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:33
 */
@Repository
public interface OrderItemMapper extends Mapper<OrderItem>, IdListMapper<OrderItem, Long> {
    /**
     * 根据订单id查询订单项集合
     * @param orderId 订单id
     * @return 订单项集合
     */
    List<OrderItem> selectByOrderId(@Param("orderId") Long orderId);

    /**
     * 根据订单id集合查询订单项集合
     * @param orderIds 订单id集合
     * @return 订单项集合
     */
    List<OrderItem> selectByOrderIds(@Param("orderIds") List<Long> orderIds);
}
