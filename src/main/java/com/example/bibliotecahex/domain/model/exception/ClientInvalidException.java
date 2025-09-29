package com.example.bibliotecahex.domain.model.exception;

public class ClientInvalidException extends RuntimeException {
    public ClientInvalidException(String message) {
        super(message);
    }
}