package com.example.processfile.controller.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "API de pedidos",
        version = "1.0",
        description = "Documentação da API de pedidos"
    )
)
public class OpenAPIConfig {
}
