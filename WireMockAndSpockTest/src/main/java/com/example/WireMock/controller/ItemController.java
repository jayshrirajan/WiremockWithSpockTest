package com.example.WireMock.controller;

import com.example.WireMock.model.Item;
import com.example.WireMock.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/wiremock/items")
    public Item getItem() {
        return itemService.getItem();
    }









}