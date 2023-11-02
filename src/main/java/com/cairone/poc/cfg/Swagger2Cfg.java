package com.cairone.poc.cfg;

import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger2Cfg implements OpenApiCustomizer {

    @Override
    public void customise(OpenAPI openApi) {
        openApi.getInfo().title("Azure POC Crud App");
    }
}
