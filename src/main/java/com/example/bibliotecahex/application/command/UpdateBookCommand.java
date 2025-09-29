package com.example.bibliotecahex.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateBookCommand {
    private String id;
    private String name;
    private String author;
    private String isbn;
    private Boolean status;
}