package com.example.bibliotecahex.infrastructure.out.persistence.mapper;

import com.example.bibliotecahex.domain.model.entity.Book;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookEntityMapper {

    public BookEntity toEntity(Book dto) {
        BookEntity entity = new BookEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAuthor(dto.getAuthor());
        entity.setIsbn(dto.getIsbn());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public Book toBook(BookEntity entity) {
        Book book = new Book(
                null,
                entity.getName(),
                entity.getAuthor(),
                entity.getIsbn(),
                entity.getStatus()
        );
        book.setId(entity.getId());
        return book;
    }
}