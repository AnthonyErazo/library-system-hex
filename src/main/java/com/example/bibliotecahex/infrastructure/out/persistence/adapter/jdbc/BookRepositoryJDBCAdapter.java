package com.example.bibliotecahex.infrastructure.out.persistence.adapter.jdbc;

import com.example.bibliotecahex.infrastructure.out.persistence.repository.jdbc.BookRepositoryJDBC;
import com.example.bibliotecahex.infrastructure.out.persistence.mapper.BookEntityMapper;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.BookEntity;
import com.example.bibliotecahex.domain.port.out.persistent.BookRepository;
import com.example.bibliotecahex.domain.model.entity.Book;

import java.util.List;
import java.util.Optional;

public class BookRepositoryJDBCAdapter implements BookRepository {

    private final BookRepositoryJDBC bookRepositoryJDBC;
    private final BookEntityMapper mapper;

    public BookRepositoryJDBCAdapter(BookRepositoryJDBC bookRepositoryJDBC, BookEntityMapper mapper) {
        this.bookRepositoryJDBC = bookRepositoryJDBC;
        this.mapper = mapper;
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = this.bookRepositoryJDBC.save(mapper.toEntity(book));
        return mapper.toBook(entity);
    }

    @Override
    public Book update(Book book) {
        BookEntity entity = this.bookRepositoryJDBC.update(mapper.toEntity(book));
        return mapper.toBook(entity);
    }

    @Override
    public void delete(String id) {
        this.bookRepositoryJDBC.delete(id);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return this.bookRepositoryJDBC.existsByIsbn(isbn);
    }

    @Override
    public Optional<Book> findById(String bookId) {
        return this.bookRepositoryJDBC.findById(bookId)
                .map(mapper::toBook);
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepositoryJDBC.findAll()
                .stream()
                .map(mapper::toBook)
                .toList();
    }
}