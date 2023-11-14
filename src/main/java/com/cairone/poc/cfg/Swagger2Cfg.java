package com.cairone.poc.cfg;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.*;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

//@Configuration
@Deprecated
public class Swagger2Cfg implements OpenApiCustomizer {

    @Override
    public void customise(OpenAPI openApi) {
        openApi
                //.components(getComponents())
                //.security(Arrays.asList(new SecurityRequirement().addList("sec")))
                .getInfo().title("Azure POC Crud App");
    }

    private Components getComponents() {
        return new Components()
                .addSecuritySchemes("sec", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .bearerFormat("JWT")
                        .scheme("bearer")
                );
                /*
                .addSecuritySchemes("sec", new SecurityScheme()
                        .type(SecurityScheme.Type.OAUTH2)
                        .description("Entra ID")
                        .flows(new OAuthFlows()
                                .authorizationCode(new OAuthFlow()
                                        .authorizationUrl("https://login.microsoftonline.com/c67e11e4-84c1-40e0-bba9-76e85edc72a8/oauth2/v2.0/authorize")
                                        .tokenUrl("https://login.microsoftonline.com/c67e11e4-84c1-40e0-bba9-76e85edc72a8/oauth2/v2.0/token")
                                        .scopes(new Scopes().addString("api://9e497156-6ecf-46d1-b577-9385c44fb3f2/Files.All", "Files full access"))
                                )
                        )
                );
                 */
    }
}
