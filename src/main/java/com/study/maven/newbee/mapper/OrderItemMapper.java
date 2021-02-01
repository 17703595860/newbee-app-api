package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.OrderItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:33
 */
@Repository
public interface OrderItemMapper extends Mapper<OrderItem> {
}
