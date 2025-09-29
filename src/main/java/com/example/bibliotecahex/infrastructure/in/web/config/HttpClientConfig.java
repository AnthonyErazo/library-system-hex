package com.example.bibliotecahex.infrastructure.in.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class HttpClientConfig {

    @Bean
    public HttpClient httpClient(){
        return  HttpClient.newHttpClient();
    }
}