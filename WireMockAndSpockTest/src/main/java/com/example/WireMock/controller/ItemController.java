package com.example.WireMock.controller;

import com.example.WireMock.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

//    private final Item item;
//
//    @Autowired
//    public ItemController(Item item) {
//
//        this.item = item;
//    }
//
//    @GetMapping("/wiremock/items")
//    public Item getItem() {
//
//        return item;
//    }


    @Value("${item.code}")
    private String code;

    @Value("${item.name}")
    private String name;

    @Value("${item.quantity}")
    private int quantity;

    @Value("${item.cost}")
    private double cost;
    @Value("${item.id}")
    private int id;


    @GetMapping("/wiremock/items")
    public Item getItem() {
        Item item = new Item();
        item.setId(id);
        item.setCode(code);
        item.setName(name);
        item.setQuantity(quantity);
        item.setCost(cost);
        return item;
    }






}