package com.example.bibliotecahex.infrastructure.in.web.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private String id;
    private LocalDateTime orderDate;
    private ClientResponse client;
    private Boolean status;
    private List<BookResponse> books;
}