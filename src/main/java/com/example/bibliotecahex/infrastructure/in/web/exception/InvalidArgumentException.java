package com.example.bibliotecahex.infrastructure.in.web.exception;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException(String message) {
        super(message);
    }
}