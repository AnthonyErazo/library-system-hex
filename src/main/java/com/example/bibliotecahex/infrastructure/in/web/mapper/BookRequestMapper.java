package com.example.bibliotecahex.infrastructure.in.web.mapper;

import com.example.bibliotecahex.application.command.CreateBookCommand;
import com.example.bibliotecahex.application.command.UpdateBookCommand;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.book.CreateBookRequest;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.book.UpdateBookRequest;

public class BookRequestMapper {

    private BookRequestMapper() {}

    public static CreateBookCommand toCommand(CreateBookRequest request) {
        return new CreateBookCommand(
                request.getName(),
                request.getAuthor(),
                request.getIsbn(),
                request.getStatus()
        );
    }

    public static UpdateBookCommand toCommand(UpdateBookRequest request) {
        return new UpdateBookCommand(
                request.getId(),
                request.getName(),
                request.getAuthor(),
                request.getIsbn(),
                request.getStatus()
        );
    }
}
