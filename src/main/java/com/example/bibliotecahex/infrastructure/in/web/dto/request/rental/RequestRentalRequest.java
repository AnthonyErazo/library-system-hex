package com.example.bibliotecahex.infrastructure.in.web.dto.request.rental;

public class RequestRentalRequest {
    public String clientDni;
    public String bookId;

    public RequestRentalRequest() {}

    public RequestRentalRequest(String clientDni, String bookId) {
        this.clientDni = clientDni;
        this.bookId = bookId;
    }

    public String getClientDni() { return clientDni; }
    public void setClientDni(String clientDni) { this.clientDni = clientDni; }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }
}