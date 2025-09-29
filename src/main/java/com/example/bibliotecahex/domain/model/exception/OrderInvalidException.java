package com.example.bibliotecahex.domain.model.exception;

public class OrderInvalidException extends RuntimeException {
    public OrderInvalidException(String message) {
        super(message);
    }
}