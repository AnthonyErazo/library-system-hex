package com.example.bibliotecahex.infrastructure.out.persistence.adapter.jpa;


import org.springframework.stereotype.Component;
import com.example.bibliotecahex.domain.model.entity.Book;
import com.example.bibliotecahex.domain.port.out.persistent.BookRepository;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.BookEntity;
import com.example.bibliotecahex.infrastructure.out.persistence.mapper.BookEntityMapper;
import com.example.bibliotecahex.infrastructure.out.persistence.repository.jpa.BookRepositoryJPA;
import java.util.List;
import java.util.Optional;

@Component
public class BookRepositoryJPAAdapter implements BookRepository {

    private final BookRepositoryJPA jpaRepository;
    private final BookEntityMapper mapper;

    public BookRepositoryJPAAdapter(BookRepositoryJPA jpaRepository, BookEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = this.jpaRepository.save(mapper.toEntity(book));
        return mapper.toBook(entity);
    }

    @Override
    public Book update(Book book) {
        return save(book);
    }

    @Override
    public void delete(String id) {
        this.jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return this.jpaRepository.existsByIsbn(isbn);
    }

    @Override
    public Optional<Book> findById(String id) {
        return this.jpaRepository.findById(id)
                .map(mapper::toBook);
    }

    @Override
    public List<Book> findAll() {
        return this.jpaRepository.findAll()
                .stream()
                .map(mapper::toBook)
                .toList();
    }
}
