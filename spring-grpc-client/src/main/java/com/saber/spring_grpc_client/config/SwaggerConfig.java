package com.saber.spring_grpc_client.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .description("grpc client api")
                        .summary("grpc client api")
                        .title("grpc client api")
                        .version("version 1.0.0 1403/08/30"));
    }
}
