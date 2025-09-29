package com.example.bibliotecahex.domain.port.out.persistent;

import com.example.bibliotecahex.domain.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Book update(Book book);
    void delete(String id);
    boolean existsByIsbn(String isbn);
    Optional<Book> findById(String bookId);
    List<Book> findAll();

}