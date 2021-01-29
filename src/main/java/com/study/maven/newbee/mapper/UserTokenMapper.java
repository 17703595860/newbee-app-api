package com.study.maven.newbee.mapper;

import com.study.maven.newbee.entity.UserToken;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/25 22:51:18
 */
@Repository
public interface UserTokenMapper extends Mapper<UserToken> {
}
