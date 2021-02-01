package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.Order;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/2/1 14:32
 */
@Repository
public interface OrderMapper extends Mapper<Order> {
}
