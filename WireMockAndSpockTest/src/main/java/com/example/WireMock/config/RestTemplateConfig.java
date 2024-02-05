package com.example.WireMock.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Value("${connect-timeout}")
    private int connectTimeout;

    @Value("${read-timeout}")
    private int readTimeout;

    @Bean
    public RestTemplate restTemplate(){
        // Set connection timeout and read timeout from properties
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        return  new RestTemplate();
    }
    @Bean
    public ClientHttpRequestInterceptor LoggingInterceptor(){
        return new LoggingInterceptor();
    }

}

