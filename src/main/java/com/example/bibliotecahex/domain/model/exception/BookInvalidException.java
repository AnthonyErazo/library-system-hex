package com.example.bibliotecahex.domain.model.exception;

public class BookInvalidException extends RuntimeException {
    public BookInvalidException(String message) {
        super(message);
    }
}