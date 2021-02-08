package com.study.maven.newbee.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.maven.newbee.config.entity.JwtProperties;
import com.study.maven.newbee.config.entity.LoginProperties;
import com.study.maven.newbee.config.handler.CurrentPageMethodArgumentResolver;
import com.study.maven.newbee.config.handler.PageSizeMethodArgumentResolver;
import com.study.maven.newbee.config.handler.TokenToUserMethodArgumentResolver;
import com.study.maven.newbee.config.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
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
    private PageSizeMethodArgumentResolver pageSizeMethodArgumentResolver;
    @Autowired
    private CurrentPageMethodArgumentResolver currentPageMethodArgumentResolver;
    @Autowired
    private LoginProperties loginProperties;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 修改全局ObjectMapper配置 jackson
     *      取消null字段的序列化
     */
    @PostConstruct
    public void objectMapper() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 跨域
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 拦截的请求
                .allowedOrigins("http://localhost:9001", "http://127.0.0.1:9001") // 允许跨域列表
                .allowCredentials(true)     // 允许携带cookie
                .allowedMethods("GET", "POST", "DELETE", "PUT", "HEAD", "OPTIONS")        // 允许的请求方式
                .allowedHeaders("*")        // 允许的请求头
                .maxAge(3600);              // 超时时间
    }

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
//        resolvers.add(pageSizeMethodArgumentResolver);
//        resolvers.add(currentPageMethodArgumentResolver);
    }


}
