package com.study.maven.newbee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ParameterBuilderPlugin;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author HLH
 * @email 17703595860@163.com
 * @date : Created in  2021/1/29 11:39
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        // 记录token
        // 创建一个swagger变量
        Parameter tokenParameter = new ParameterBuilder()
                .name("Authentication").description("用户认证token")
                .modelRef(new ModelRef("String")).parameterType("header")
                .required(false).build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study.maven.newbee.controller"))
                .paths(PathSelectors.any())
                .build();
        // 放入
        ArrayList<Parameter> params = new ArrayList<>();
        params.add(tokenParameter);
        docket.globalOperationParameters(params);
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("新峰商场swagger文档")
                .description("swagger文档 by HLH")
                .version("1.0")
                .build();
    }

}
