package com.smartwaste.identityservice.common;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI identityServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SmartWaste - Identity Service API")
                        .description("Identity and Authentication Service for Smart City Waste and Recycling Manager")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("SmartWaste Team")));
    }
}
