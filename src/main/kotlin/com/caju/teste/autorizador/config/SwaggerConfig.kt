package com.caju.teste.autorizador.config


import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
                .components(Components()
                        .addSecuritySchemes("basicScheme",
                                SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .addSecurityItem(SecurityRequirement().addList("basicScheme"))
                .info(Info().title("API Caju Autorizador")
                        .description("API para o sistema Caju")
                        .version("v1.0.0")
                        .license(License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(ExternalDocumentation()
                        .description("Documentação completa")
                        .url("https://github.com/andreLSantini/miniautorizador"))
    }
}