package com.factory.backend.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public GroupedOpenApi postgresApi() {
        return GroupedOpenApi.builder()
                .group("postgres")
                .pathsToMatch("/postgres/**")
                .build();
    }

    @Bean
    public GroupedOpenApi mongoApi() {
        return GroupedOpenApi.builder()
                .group("mongo")
                .pathsToMatch("/mongo/**")
                .build();
    }
}
