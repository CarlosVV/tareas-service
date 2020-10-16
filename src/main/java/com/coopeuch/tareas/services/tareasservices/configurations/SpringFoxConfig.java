package com.coopeuch.tareas.services.tareasservices.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.coopeuch.tareas.services.tareasservices"))
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Tareas REST API",
                "Tareas Service",
                "1.0.0",
                "www.google.com",
                new Contact("Carlos Valderrama","https://www.linkedin.com/in/carlos-miguel-vega/","cvalderramav@gmail.com"),
                "MIT License",
                "www.google.com",
                Collections.emptyList()
        );
    }
}
