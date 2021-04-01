package com.topwordanalysis.Config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: swaggerconfig
 * @Description:
 * @author: 黄贸之
 * @date: 2021/3/30 16:37
 * @Github: https://github.com/h2333
 */

@Configuration
@EnableSwagger2//开启Swagger2
public class swaggerconfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .pathMapping("/")
            .select() // 选择那些路径和api会生成document
            .apis(RequestHandlerSelectors.any())// 对所有api进行监控
            //不显示错误的接口地址
            .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
            .paths(PathSelectors.regex("/.*"))// 对根下所有路径进行监控
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("测试")
            .contact(new Contact("romeo", "", "xxx@qq.com"))
            .description("接口文档")
            .termsOfServiceUrl("NO terms of service")
            .license("The Apache License, Version 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("v1.0")
            .build();
    }
}