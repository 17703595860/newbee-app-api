package com.study.maven.newbee.service;

import com.study.maven.newbee.vo.OrderParamVO;

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
     * @return 生成订单的id
     */
    Long generateOrder(OrderParamVO orderParamVO, Long userId);
}
