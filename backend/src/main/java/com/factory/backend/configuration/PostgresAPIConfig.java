package com.factory.backend.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Postgres API",
                description = "API responsible for interaction with PostgreSQL DB", version = "1.0.0",
                contact = @Contact(
                        name = "Danila Lisichkin",
                        email = "lisichkindanila@gmail.com"
                )
        )
)
@Configuration
public class PostgresAPIConfig {
    @Bean
    public GroupedOpenApi postgresApi() {
        return GroupedOpenApi.builder()
                .group("postgres")
                .pathsToMatch("/postgres/**")
                .build();
    }
}
