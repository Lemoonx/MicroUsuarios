package com.nspTECH.gestion_usuarios.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration

public class WebClientConfig {
@Bean

    public WebClient webClient(){
        return WebClient.builder()
        .baseUrl("http://localhost:8081/api/v1/Pedidos")
        .build();

    }
}
