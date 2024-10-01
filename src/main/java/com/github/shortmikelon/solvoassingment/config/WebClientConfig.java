package com.github.shortmikelon.solvoassingment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient beanWebClient() {
        String BASE_URL = "https://v6.exchangerate-api.com/v6/4d1b395ec71f4b32720c7cd1";
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }
}
