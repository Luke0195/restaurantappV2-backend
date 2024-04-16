package br.com.waiterapp.application.config;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class SwaggerConfig {


    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder().group("waiterapp").pathsToMatch("/**").build();
    }

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(new Info().title("WaiterApp").version("V1"));
    }
}

