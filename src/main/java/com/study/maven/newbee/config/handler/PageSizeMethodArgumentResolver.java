package com.study.maven.newbee.config.handler;

import com.study.maven.newbee.config.annotation.PageSize;
import com.study.maven.newbee.config.entity.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 方法参数解析器，标注@PageSize注解的参数控制
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 20:14:57
 */
@Component
public class PageSizeMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private Constants constants;

    /**
     * 如果标注了@Page注解，入参判断
     * @param parameter 参数
     * @return true，拦截，false，放行
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(PageSize.class);
    }

    /**
     * 入参判断
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        PageSize pageSizeAnno = parameter.getParameterAnnotation(PageSize.class);
        if (pageSizeAnno != null) {
            // 获取注解中指定的名称
            String pageSizeName = pageSizeAnno.value();
            // 获取对应的请求参数
            String pageSize = webRequest.getParameter(pageSizeName);
            // 如果没有值，返回配置文件中的值
            return StringUtils.isBlank(pageSize) || StringUtils.equals(pageSize, "0") ? constants.getPageSize() : Integer.valueOf(pageSize);
        }
        return null;
    }
}
