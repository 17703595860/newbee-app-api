# newbee-app-api
新蜂商城-手机端-后台，采用springboot+mysql+mybatis+通用mapper

接口地址：http://localhost:9002

swagger文档地址：http://localhost:9002/swagger-ui.html

# newbee-app-web

新蜂商城-手机端-前台，使用vue-cli3，vue2，vant

启动地址：http://localhost:9001/#/login

# 项目前置知识

## 1. Swagger

### 1.1 介绍

Swagger-ui是openApi的统一规范的实现，可以使用注解的方式生成统一的接口文档，可以在线浏览文档，测试接口等等

### 1.2 使用

引入依赖

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.8.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.8.0</version>
</dependency>
```

编写swagger配置文件SwaggerConfig

```java
package com.study.maven.newbee.config;

import com.study.maven.newbee.config.entity.Constants;
import org.springframework.beans.factory.annotation.Autowired;
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
import springfox.documentation.swagger.web.*;
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

    @Autowired
    private Constants constants;

    @Bean
    public Docket api() {

        // 记录token
        // 创建一个swagger变量
        Parameter tokenParameter = new ParameterBuilder()
                .name(constants.getTokenHeaderName()).description("用户认证token")
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

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                // 隐藏UI上的Models模块
                .defaultModelsExpandDepth(-1)
                .defaultModelExpandDepth(0)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .validatorUrl(null)
                .build();
    }

}
```

## 1.2 拦截器Interceptor

springMVC的拦截器Interceptor，拦截每次controller请求，可以使用springboot容器中的资源，一般用作登录拦截，日志记录，敏感字过滤

使用

写一个类，实现handlerInterceptor接口，在WebMVC配置文件中注册拦截器

```java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    // 添加登录拦截器，放行地址在yml文件中配置，通过@ConfigurationProperties读取
    registry.addInterceptor(loginHandlerInterceptor)
        		.addPathPatterns("/**").excludePathPatterns(loginProperties.getLoginRelease());
}
```

## 1.3 方法参数拦截器

spring的方法参数拦截器，基于方法参数，对每次请求的参数进行处理，结合自定义注解，实现参数自定义控制，@requestBody就是这个道理

使用

写一个类，实现HandlerMethodArgumentResolver接口，在supportsParameter方法中判断是否进行处理，比如是否标注某个注解。在resolveArgument方法中写处理逻辑

在WebMVC配置文件中注册

```java
@Override
public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    // token转user对象，使用自定义注解@TokenToUser注解，利用参数解析，自动注入user对象
    resolvers.add(tokenToUserMethodArgumentResolver);
}
```

## 1.4 雪花算法

雪花算法是一个分布式的id生成算法，为64个字节，第一位保留，后面41位作为毫秒数，然后10位为机器id和工作id，最后12位为序列号，保证了在分布式系统中的主键唯一，自增有序。每秒可以生成26万个左右的id

SnowFlake 算法，是 Twitter 开源的分布式 id 生成算法。其核心思想就是：使用一个 64 bit 的 long 型的数字作为全局唯一 id。在分布式系统中的应用十分广泛，且ID 引入了时间戳，基本上保持自增的，后面的代码中有详细的注解。

注意，要保证id生成器唯一，否则会产生重复现象，工具类开源，拿来即用

## 1.5 token+RSA

在分布式系统中，restFul接口无状态性，所以session被舍弃，因为session保存在服务器，不同发的服务器有不同的session，无法共享数据。也可以设置session的缓存序列化，比如springboot就有session的redis序列化实现，

但时现在使用token，用户登录之后，颁发token，用户请求携带token，根据token就可以解析出存储在token中的登录信息。jwt类型token，分为3部分，第一部分为token类型生明，第二部分为载荷信息，和签发人，签发时间等信息，第三部分是第一二部分经过加密形成的，保证数据合法性，前两部分使用base64加密

所以token的加密是重要的，可以使用RSA非对称加密，使用私钥进行加密，使用公钥进行解密。保证token加密性，合法性

## 1.6 Vant

基于vue的视图层ui框架，适配手机端，好处是天然适配手机端，包含很多手机端特有的功能，比如下拉刷新，滑动加载，而且提供了很多写好的组件，比如地址管理，订单列表等等

# 项目总结

## 1. 用户中心

涉及的表有tb_newbee_mall_user和tb_newbee_mall_user_token

用户表和用户token表，这把token存放到数据库中，其实也可以不存储

用户登录之后，颁发jwt类型token，吧token发送到前台，同时保存到数据库中，同一个用户只能有一个token记录，前台使用localstorage存储，通过axios的请求拦截器，每次请求都携带token，后台通过登录拦截器，解析到登录用户信息，使用自定义注解+方法参数解析器自动注入登录用户参数

个人中心是对用户表进行增删改查操作

## 2. 用户地址

涉及的表有tb_newbee_mall_user_address

对用户地址进行增删改查，一个用户只能有一个默认地址，如果已经存在默认地址，有覆盖行为，如果没有默认地址，则在下单页面需要选择地址，否则有默认地址选中。

## 3. 首页信息展示

首页信息只负责展示，相对比较简单，只需要拼接对应的数据VO对象，返回即可。有轮播图参数，热门商品，新品推荐，推荐商品等等

涉及到首页配置表tb_newbee_mall_index_config，轮播图表tb_newbee_mall_carousel，轮播图表存放首页轮播图，关联商品id，可以限制轮播图数量，首页配置表，通过type字段规定是首页中的哪一个模块的数据，关联商品id

## 4. 商品分类

商品分类表tb_newbee_mall_goods_category

三级分类，前台通过vue渲染，使用level直接表明是几级分类，然后使用parent_id指定父级id

还可以使用两个字段，parent_id指定父级id，is_parent是否为父级，根据两个字段就可以判断三级分类，是父级，父级为0位以及节点。是父级，父级有值，为二级分类。有父节点，不是父节点为三级分类。上面的一种更为简单

这个项目中没有品牌的划分

## 5. 商品搜索

首页和分类页面可以进入商品搜索页面，根据商品查询参数（商品名称），分类id，排序参数分类，首页过来，没有分类id，分类页面过来，携带分类id

在这，使用的是vue组件传值的方式，props绑定方式做的，也可以使用地址栏拼参数，然后从地址栏获取实现

## 6. 商品列表

涉及到的表有tb_newbee_mall_goods_info商品表，这只做简单描述，没有spu、sku、规格参数等专业词汇，直接使用一张表，大概描述

通过商品搜索后，进入商品列表，

前台使用vant提供的下拉刷新和下拉加载下一页功能实现

后台拼装对应的商品对象，点击进入商品详情

商品详情，因为没有规格参数和品牌信息，所以查询商品VO即可

这所有的表都是用软删除（这有可能代码中做的不是软删除）

## 7. 购物车

下一步就是加入购物车，涉及到的表是tb_newbee_mall_shopping_cart_item

这存储数据库，其实这可以不存数据库，存储redis缓存中。但是会存在未登录购物车，登录购物车合并问题

对购物车商品增删改查

加入购物车比较商品是否已经下架，购物车是否属于当前用户

## 8. 下单

涉及到的表是tb_newbee_mall_order，tb_newbee_mall_order_item，tb_newbee_mall_order_address表，订单主表，存放订单状态变化，支付状态，发货状态等等，也可以分开存放，订单详情表，存放一个订单中的多个商品，主要起快照作用。订单地址表，是当前订单选择的收货地址，也是起到快照的作用

支付方式为微信支付或者支付宝支付，这仅做模拟实现，支付成功为待发货，未支付，支付失败为支付失败状态

## 9. 订单管理

使用Vant的van-tabs做，下面内容使用下拉刷新加滑动加载

根据订单状态，分为不同的tab

拼装订单信息，订单详情信息即可

订单详情页面，也是订单信息和订单详情信息，可以根据订单不同状态显示不同按钮，如果待支付，可以支付，待收货，可以收货，可以取消订单



至此，一套简单的流程已经可以了，手机端主要负责展示，其实并不复杂。