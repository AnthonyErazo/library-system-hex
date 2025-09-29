package com.example.bibliotecahex.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private String id;
    private LocalDateTime orderDate;
    private ClientDto client;
    private Boolean status;
    private List<BookDto> books;
}