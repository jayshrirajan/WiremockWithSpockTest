package com.example.WireMock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogController {

    public static Logger logger = LoggerFactory.getLogger(LogController.class);
    public void index() {
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");
    }
}