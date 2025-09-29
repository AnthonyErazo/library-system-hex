package com.example.bibliotecahex.infrastructure.out.persistence.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
