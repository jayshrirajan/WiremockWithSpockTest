package com.example.WireMock.service;

import com.example.WireMock.model.Item;
import com.example.WireMock.model.VendItemRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> initializeItems(List<Item> itemList) {
        String initializeUrl = baseUrl + "/initialize";
        HttpEntity<List<Item>> entity = new HttpEntity<>(itemList);
        return restTemplate.exchange(initializeUrl, HttpMethod.POST, entity, String.class);
    }
    public ResponseEntity<String> addChange(Map<Double, Integer> denominations) {
        String addChangeUrl = baseUrl + "/add-change";
        HttpEntity<Map<Double, Integer>> entity = new HttpEntity<>(denominations);
        return restTemplate.exchange(addChangeUrl, HttpMethod.POST, entity, String.class);
    }
    public ResponseEntity<String> vendItems(List<VendItemRequest> vendItemRequests) {
        String vendItemsUrl = baseUrl + "/vend-items";
        HttpEntity<List<VendItemRequest>> entity = new HttpEntity<>(vendItemRequests);
        return restTemplate.exchange(vendItemsUrl, HttpMethod.PUT, entity, String.class);
    }


}
