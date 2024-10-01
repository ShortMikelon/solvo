package com.github.shortmikelon.solvoassingment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final String BASE_URL = "https://v6.exchangerate-api.com/v6/4d1b395ec71f4b32720c7cd1";

    @Bean
    public WebClient beanWebClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
