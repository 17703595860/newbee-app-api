package com.study.maven.newbee.config;

import com.study.maven.newbee.config.entity.JwtProperties;
import com.study.maven.newbee.config.entity.LoginProperties;
import com.study.maven.newbee.config.handler.TokenToUserMethodArgumentResolver;
import com.study.maven.newbee.config.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/29 17:11
 */
@Configuration
@PropertySource({"classpath:application.yml"})
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;
    @Autowired
    private TokenToUserMethodArgumentResolver tokenToUserMethodArgumentResolver;
    @Autowired
    private LoginProperties loginProperties;

    /**
     * 添加登录拦截器
     * @param registry 拦截器列表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginHandlerInterceptor)
            .addPathPatterns("/**").excludePathPatterns(loginProperties.getLoginRelease());
    }

    /**
     * 添加参数解析器
     * @param resolvers 参数解析器列表
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(tokenToUserMethodArgumentResolver);
    }
}
