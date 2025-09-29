package com.example.bibliotecahex.infrastructure.in.messaging.dto;

public class RentalRequestedEvent {
    public String requestId;
    public String clientDni;
    public String bookId;
    public String timestamp;

    public RentalRequestedEvent() {}

    public RentalRequestedEvent(String requestId, String clientDni, String bookId, String timestamp) {
        this.requestId = requestId;
        this.clientDni = clientDni;
        this.bookId = bookId;
        this.timestamp = timestamp;
    }
}