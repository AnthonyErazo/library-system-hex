package com.example.bibliotecahex.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private String id;
    private String name;
    private String author;
    private String isbn;
    private Boolean status;
}