package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/25 22:49:41
 */
@Repository
public interface UserMapper extends Mapper<User> {



}
