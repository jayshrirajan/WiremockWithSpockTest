package com.example.WireMock.model;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "item")
public class Item {

//    private int idItem;
//    private String itemCode;
//    private String itemName;
//    private int quantity;
//    private double itemCost;
//
//
//    public Item(int idItem, String itemCode, String itemName, int quantity, double itemCost) {
//        this.idItem = idItem;
//        this.itemCode = itemCode;
//        this.itemName = itemName;
//        this.quantity = quantity;
//        this.itemCost = itemCost;
//    }
//
//    public int getIdItem() {
//
//        return idItem;
//    }
//
//    public void setIdItem(int idItem) {
//        this.idItem = idItem;
//    }
//
//    public String getItemCode() {
//
//        return itemCode;
//    }
//
//    public void setItemCode(String itemCode) {
//
//        this.itemCode = itemCode;
//    }
//
//    public String getItemName() {
//
//        return itemName;
//    }
//
//    public void setItemName(String itemName) {
//
//        this.itemName = itemName;
//    }
//
//    public int getQuantity() {
//
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//
//        this.quantity = quantity;
//    }
//
//    public double getItemCost() {
//
//        return itemCost;
//    }
//
//    public void setItemCost(double itemCost) {
//
//        this.itemCost = itemCost;
//    }
//    @Override
//    public String toString() {
//        return "Item{" +
//                "idItem=" + idItem +
//                ", itemCode='" + itemCode + '\'' +
//                ", itemName='" + itemName + '\'' +
//                ", quantity=" + quantity +
//                ", itemCost=" + itemCost +
//                '}';
//    }
    private int id;
    private String code;
    private String name;
    private int quantity;
    private double cost;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }




}
