package com.cairone.poc.cfg;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerCfg {

    @Value("${authorization-url}")
    private String authorizationUrl;
    @Value("${token-url}")
    private String tokenUrl;

    @Bean
    public OpenAPI getOpenAPI() {
        return new OpenAPI()
            .components(new Components()
                    .addSecuritySchemes("http", getSecuritySchemeHttpType())
                    .addSecuritySchemes("oauth", getSecuritySchemeOauth2Type())
            )
            .security(Arrays.asList(
                    new SecurityRequirement().addList("http"),
                    new SecurityRequirement().addList("oauth")))
            .info(new Info().title("Azure POC Crud App"))
            ;
    }

    private SecurityScheme getSecuritySchemeHttpType() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    private SecurityScheme getSecuritySchemeOauth2Type() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2)
                .description("Entra ID")
                .flows(new OAuthFlows()
                        .authorizationCode(new OAuthFlow()
                                .authorizationUrl(authorizationUrl)
                                .tokenUrl(tokenUrl)
                                .scopes(new Scopes().addString("api://9e497156-6ecf-46d1-b577-9385c44fb3f2/Files.All", "Files full access"))
                        )
                );
    }
}
