package com.example.WireMock.controller;

import com.example.WireMock.exception.*;
import com.example.WireMock.model.Item;
import com.example.WireMock.model.VendItemRequest;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
public class RestTemplateController {

    @Autowired
    public RestTemplate restTemplate;
    static final String VM_URL = "http://localhost:8080/vending-machine/";

    private static final Logger log = LoggerFactory.getLogger(RestTemplateController.class);


    //    @GetMapping("rest/totalItem")
//    public String getTotalItems()
//    {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> responseEntity = restTemplate
//                .exchange(  VM_URL+"total-items", HttpMethod.GET, entity, String.class);
//        return responseEntity.getBody();
//    }
    @GetMapping("rest/totalItem")
    public String getTotalItems() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> responseEntity = restTemplate
                    .exchange(VM_URL + "total-items", HttpMethod.GET, entity, String.class);

            String responseBody = responseEntity.getBody();
            log.info("Successfully retrieved total items. Response: {}", responseBody);
            return responseBody;
        } catch (Exception e) {
            log.error("Error while getting total items", e);
            throw e;
        }
    }


    @GetMapping("rest/getItem")
    public String getItem() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(VM_URL + "get-item", HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }


    @PostMapping("rest/initialize")
    public String initialize(@RequestBody List<Item> itemList) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<Item>> entity = new HttpEntity<>(itemList, headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(VM_URL + "initialize", HttpMethod.POST, entity, String.class);

        return responseEntity.getBody();
    }

    @PostMapping("rest/Change")
    public String addChange(@RequestBody Map<Double, Integer> denominations) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<Double, Integer>> entity = new HttpEntity<>(denominations, headers);

        ResponseEntity<String> responseEntity = restTemplate
                .exchange(VM_URL + "add-change", HttpMethod.POST, entity, String.class);

        return responseEntity.getBody();
    }


//    @PutMapping("rest/vendItem")
//    public ResponseEntity<String> vendItem(@RequestBody List<VendItemRequest> vendItemRequests) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        try {
//
//            HttpEntity<List<VendItemRequest>> entity = new HttpEntity<>(vendItemRequests, headers);
//
//            ResponseEntity<String> responseEntity = restTemplate.exchange(VM_URL +"vend-items", HttpMethod.PUT, entity, String.class);
//
//            return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
//
//        } catch (HttpClientErrorException e) {
//
//            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());
//
//        } catch (ItemNotFoundException | ItemOutOfStockException e) {
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        } catch (InsufficientBalanceException | UnableToReturnBalanceException e) {
//
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//        }

    @PutMapping("rest/vendItem")
    public ResponseEntity<String> vendItem(@RequestBody List<VendItemRequest> vendItemRequests) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            log.info("VendItem request received. Number of items: {}", vendItemRequests.size());

            HttpEntity<List<VendItemRequest>> entity = new HttpEntity<>(vendItemRequests, headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(VM_URL + "vend-items", HttpMethod.PUT, entity, String.class);
            log.info("VendItem request successful. Response: {}", responseEntity.getBody());
            return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());

        } catch (HttpClientErrorException e) {
            log.error("Error while processing VendItem request. Status Code: {}, Response: {}", e.getRawStatusCode(), e.getResponseBodyAsString());
            return ResponseEntity.status(e.getRawStatusCode()).body(e.getResponseBodyAsString());

        } catch (ItemNotFoundException | ItemOutOfStockException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (InsufficientBalanceException | UnableToReturnBalanceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.error("Unexpected error occurred: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


}




































































































