package com.study.maven.newbee.service.impl;

import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.entity.UserToken;
import com.study.maven.newbee.exception.AuthenticationException;
import com.study.maven.newbee.mapper.UserMapper;
import com.study.maven.newbee.mapper.UserTokenMapper;
import com.study.maven.newbee.service.UserService;
import com.study.maven.newbee.config.entity.JwtProperties;
import com.study.maven.newbee.utils.JwtUtils;
import com.study.maven.newbee.utils.PayLoad;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/25 22:58:29
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public String login(String username, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginName", username);
        User daoUser = userMapper.selectOneByExample(example);
        if (daoUser == null) {
            throw new AuthenticationException("登录失败，用户名不存在");
        }
        if (!StringUtils.equals(daoUser.getPasswordMd5(), DigestUtils.md5Hex(password))) {
            throw new AuthenticationException("登录失败，密码错误");
        }
        if (daoUser.getLockedFlag()) {
            throw new AuthenticationException("登录失败，账号已经被锁定");
        }
        if (daoUser.getIsDeleted()) {
            throw new AuthenticationException("登录失败，账号已经被删除");
        }
        daoUser.setPasswordMd5(null);
        String token = null;
        try {
            token = JwtUtils.generateToken(PayLoad.builder().user(daoUser).expire(jwtProperties.getExpire()).build(), jwtProperties.getPrivateKey(), jwtProperties.getExpire());
        } catch (Exception e) {
            throw new AuthenticationException("登录失败，生成token失败");
        }
        DateTime now = DateTime.now();
        DateTime expirTime = now.plusMinutes(jwtProperties.getExpire());
        UserToken userToken = new UserToken(daoUser.getUserId(), token, now.toDate(), expirTime.toDate());
        int flag = 0;
        if (userTokenMapper.selectByPrimaryKey(userToken.getUserId()) == null){
            flag = userTokenMapper.insertSelective(userToken);
        } else {
            flag =userTokenMapper.updateByPrimaryKeySelective(userToken);
        }
        if (flag != 1) {
            throw new AuthenticationException("登录失败，插入token信息失败");
        }
        return token;
    }

}
