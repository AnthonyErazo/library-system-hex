package com.example.bibliotecahex.application.mapper;

import org.springframework.stereotype.Component;

import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.domain.model.entity.Book;

@Component
public class BookToBookDto {

    public BookDto buildBookDto(Book book) {
        BookDto response = new BookDto();
        response.setId(book.getId());
        response.setName(book.getName());
        response.setAuthor(book.getAuthor());
        response.setIsbn(book.getIsbn());
        response.setStatus(book.getStatus());

        return response;
    }
}