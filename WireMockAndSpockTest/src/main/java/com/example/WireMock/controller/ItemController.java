package com.example.WireMock.controller;

import com.example.WireMock.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    @Value("${item.itemCode}")
    private String itemCode;

    @Value("${item.itemName}")
    private String itemName;

    @Value("${item.quantity}")
    private int quantity;

    @Value("${item.itemCost}")
    private double itemCost;
    @Value("${item.idItem}")
    private int idItem;


    @GetMapping("/wiremock/items")
    public Item getItem() {
        Item item = new Item();
        item.setIdItem(idItem);
        item.setItemCode(itemCode);
        item.setItemName(itemName);
        item.setQuantity(quantity);
        item.setItemCost(itemCost);
        return item;
    }









}