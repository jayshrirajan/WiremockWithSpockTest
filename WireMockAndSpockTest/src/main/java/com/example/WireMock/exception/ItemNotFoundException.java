package com.example.WireMock.exception;

public class ItemNotFoundException extends  RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
