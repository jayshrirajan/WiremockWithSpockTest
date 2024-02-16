package com.example.WireMock.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Handle custom exceptions
    @ExceptionHandler({
            InsufficientBalanceException.class,
            ItemNotFoundException.class,
            ItemOutOfStockException.class,
            UnableToReturnBalanceException.class
    })
    public ResponseEntity<Error> handleCustomException(Exception exception) {
        logger.error("Handling custom Exceptions:",exception);
        Error error = new Error(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Handle the UnhandledException
    @ExceptionHandler(UnhandledException.class)
    public ResponseEntity<Error> handleUnhandledException(UnhandledException exception) {
        // Log the exception details
        logger.error("An unhandled exception occurred:", exception);
        Error error = new Error("An unhandled exception occurred. Please check the logs for details.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
