package com.restapi.book.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket swaggerConfiguration()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.restapi.book.controller"))
                .build().
                        apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Assignment REST API",
                "Simple Book API Assignment with Spring Boot for learning purpose",
                "1.0.0",
                "Terms of service",
                new Contact("Md Jahid Raihan", "https://github.com/jahid1209", "jahid.raihan10@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}