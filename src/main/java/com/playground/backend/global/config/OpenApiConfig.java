package com.playground.backend.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Swagger 설정
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "PlayGround_PG",
                description = "PlayGround 프로젝트 API 문서",
                version = "v1.0.0"
        ),
        servers = {
                @Server(url = "http://localhost:8080")
        }
)
/**
 * 도메인 API 그룹
 * Controller 하위 path 포함
 */
public class OpenApiConfig {
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("User")
                .pathsToMatch("/api/users/**")
                .build();
    }
}
