package com.example.WireMock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index() {
        LOGGER.debug("This is a debug message");
        LOGGER.info("This is an info message");
        LOGGER.warn("This is a warn message");
        LOGGER.error("This is an error message");

        return "";
    }
}
