package com.study.maven.newbee.config.interceptor;

import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.entity.UserToken;
import com.study.maven.newbee.exception.AuthenticationException;
import com.study.maven.newbee.mapper.UserTokenMapper;
import com.study.maven.newbee.config.entity.JwtProperties;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  上一个系统做法，使用拦截器校验token，使用ThreadLocal存储
 *  本次做法，使用拦截器校验token，使用自定义注解+参数拦截注入登录用户
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/29 17:35
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private UserTokenMapper userTokenMapper;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = null;
        String token = request.getHeader("Authentication");
        if (StringUtils.isBlank(token)){
            throw new AuthenticationException("token传递错误");
        }
        Example example = new Example(UserToken.class);
        example.createCriteria().andEqualTo("token", token);
        UserToken userToken = userTokenMapper.selectOneByExample(example);
        if (userToken == null) {
            throw new AuthenticationException("token不存在");
        }
        DateTime now = DateTime.now();
        if (userToken.getExpireTime().getTime() - now.getMillis() <= 0) {
            // 删除token
            userTokenMapper.deleteByPrimaryKey(userToken.getUserId());
            throw new AuthenticationException("token已经过期");
        }
        // 如果token有效，刷新token
        DateTime expire = now.plusMinutes(jwtProperties.getExpire());
        userToken.setUpdateTime(now.toDate());
        userToken.setExpireTime(expire.toDate());
        userTokenMapper.updateByPrimaryKeySelective(userToken);
        return true;
    }
}
