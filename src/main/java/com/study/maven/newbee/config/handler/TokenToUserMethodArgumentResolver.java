package com.study.maven.newbee.config.handler;

import com.study.maven.newbee.config.annotation.TokenToUser;
import com.study.maven.newbee.config.entity.Constants;
import com.study.maven.newbee.entity.User;
import com.study.maven.newbee.mapper.UserMapper;
import com.study.maven.newbee.mapper.UserTokenMapper;
import com.study.maven.newbee.config.entity.JwtProperties;
import com.study.maven.newbee.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 方法参数解析器
 *      进入方法之前，解析token，注入等前登陆用户
 *         上一个系统做法，使用拦截器校验token，使用ThreadLocal存储
 *         本次做法，使用拦截器校验token，使用自定义注解+参数拦截注入登录用户
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/29 15:52
 */
@Component
public class TokenToUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private Constants constants;

    /**
     * 判断是否要进行增强
     * @param parameter 方法参数封装
     * @return 如果为true，则进入拦截，如果为false，放行
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(TokenToUser.class);
    }

    /**
     * 真正的拦截
     * @param parameter 表明注解的参数
     * @param mavContainer 用来提供访问Model
     * @param webRequest    request
     * @param binderFactory 用于创建一个WebDataBinder用于数据绑定、校验
     * @return 返回注入的参数
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (parameter.getParameterAnnotation(TokenToUser.class) != null) {
            String token = webRequest.getHeader(constants.getTokenHeaderName());
            // 获取user
            return JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey()).getUser();
        }
        return null;
    }
}
