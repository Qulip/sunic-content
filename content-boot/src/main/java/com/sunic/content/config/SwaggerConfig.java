package com.sunic.content.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI/Swagger configuration for API documentation.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI contentServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Content Service API")
                        .description("Multi-module Content Management Service")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Content Service Team")
                                .email("content-service@sunic.com")));
    }
}