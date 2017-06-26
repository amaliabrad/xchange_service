package com.xchange.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration class.
 * Created by Amalia Brad.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * Api select method.
     *
     * @return a builder which is intended to be the primary interface into the swagger-springmvc framework.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xchange.service"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata());
    }

    /**
     * The metadata information.
     *
     * @return the REST API details.
     */
    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Exchange rate service")
                .description("Contains REST APIs available to get conversion rate from Euro to any other currency provided by ECB")
                .build();
    }

}

