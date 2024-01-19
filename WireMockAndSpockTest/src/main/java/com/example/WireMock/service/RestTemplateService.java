package com.example.WireMock.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;
    @Value("${wiremock.baseUrl}")
    private final String baseUrl;
    public RestTemplateService(RestTemplate restTemplate, String baseUrl) {
            this.restTemplate = restTemplate;
            this.baseUrl = baseUrl;

        }
        public ResponseEntity<String> getTotalItems() {
            String url = baseUrl + "/totalItem";
            return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        }
        public ResponseEntity<String> getItem() {
             String url = baseUrl + "/getItem";
             return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }
}
