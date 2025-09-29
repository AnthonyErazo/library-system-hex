package com.example.bibliotecahex.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateBookCommand {

    private String name;
    private String author;
    private String isbn;
    private Boolean status;

}