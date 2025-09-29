package com.example.bibliotecahex.infrastructure.in.web.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponse {

    private String id;
    private String name;
    private String author;
    private String isbn;
    private Boolean status;

}

