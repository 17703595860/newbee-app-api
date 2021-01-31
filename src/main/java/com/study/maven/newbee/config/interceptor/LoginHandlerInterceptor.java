package com.study.maven.newbee.config.interceptor;

import com.study.maven.newbee.config.entity.Constants;
import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.entity.UserToken;
import com.study.maven.newbee.exception.AuthenticationException;
import com.study.maven.newbee.mapper.UserTokenMapper;
import com.study.maven.newbee.config.entity.JwtProperties;
import com.study.maven.newbee.utils.JwtUtils;
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
 *  这个版本使用jwt token，但是使用localstorage存储，不使用cookie，所以无法刷新token，只能固定30分钟，
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
    @Autowired
    private Constants constants;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(constants.getTokenHeaderName());
        if (StringUtils.isBlank(token)){
            throw new AuthenticationException("token传递错误");
        }
        Example example = new Example(UserToken.class);
        example.createCriteria().andEqualTo("token", token);
        UserToken userToken = userTokenMapper.selectOneByExample(example);
        if (userToken == null) {
            throw new AuthenticationException("token不存在");
        }
        // 视图解密，虽然保存数据库了，但是以jwt为主，jwt解析是会做过期时间验证，
        // 如果不使用cookie，则没有办法刷新token过期时间，
        // 如果刷新过期时间，不使用cookie，那么就会造成token发生改变，没有办法通知前台
        User user = null;
        try {
            user = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey()).getUser();
        } catch (Exception e) {
            // 删除token
            Example example1 = new Example(UserToken.class);
            example1.createCriteria().andEqualTo("token", token);
            userTokenMapper.deleteByExample(example1);
            throw new AuthenticationException("Token解析错误，请重新登录");
        }
        return true;
        // 不刷新数据库了，因为以jwt为准，刷新数据库没有作用
        /*DateTime now = DateTime.now();
        if (userToken.getExpireTime().getTime() - now.getMillis() <= 0) {
            // 删除token
            userTokenMapper.deleteByPrimaryKey(userToken.getUserId());
            throw new AuthenticationException("token已经过期");
        }
        // 如果token有效，刷新token
        DateTime expire = now.plusMinutes(jwtProperties.getExpire());
        userToken.setUpdateTime(now.toDate());
        userToken.setExpireTime(expire.toDate());
        userTokenMapper.updateByPrimaryKeySelective(userToken);*/
    }
}
