package com.syd.springboot_elstic.comfig;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
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
 * Description: springbootdemo
 * Created by ubuntu on 2020/11/20 上午10:12
 */
/*@Configuration
@EnableSwagger2
@EnableKnife4j*/
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot 使用 Swagger2 构建RESTful API")
                .description("API文档")
                .termsOfServiceUrl("")
                .contact(new Contact("songyd", "", ""))
                .version("1.0")
                .description("API 描述")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.syd.springboot_elstic.controller"))
                .paths(PathSelectors.any())
                .build();

    }
}
