package com.example.WireMock.service;

import com.example.WireMock.config.ItemConfig;
import com.example.WireMock.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final Item item;

    @Autowired
    public ItemService(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }





}
