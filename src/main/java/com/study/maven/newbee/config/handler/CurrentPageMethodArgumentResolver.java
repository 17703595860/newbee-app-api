package com.study.maven.newbee.config.handler;

import com.study.maven.newbee.config.annotation.CurrentPage;
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
 * 方法参数解析器，标注@CurrentPage注解的参数控制
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/30 20:46:04
 */
@Component
public class CurrentPageMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private Constants constants;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentPage.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        CurrentPage currentPageAnno = parameter.getParameterAnnotation(CurrentPage.class);
        if (currentPageAnno != null) {
            String currentPage = webRequest.getParameter(currentPageAnno.value());
            return currentPage == null || StringUtils.equals(currentPage, "0") ? constants.getCurrentPage() : Integer.valueOf(currentPage);
        }
        return null;
    }
}
