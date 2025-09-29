package com.example.bibliotecahex.application.usecases.book;

import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.application.exception.BusinessException;
import com.example.bibliotecahex.application.mapper.BookToBookDto;
import com.example.bibliotecahex.domain.model.entity.Book;
import com.example.bibliotecahex.domain.port.out.persistent.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetBookByIdUseCase{

    private final BookRepository bookRepository;
    private final BookToBookDto mapper;

    public GetBookByIdUseCase(BookRepository bookRepository, BookToBookDto mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public BookDto execute(String bookId) {

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isEmpty()) {
            throw new BusinessException("Book not found");
        }

        return mapper.buildBookDto(optionalBook.get());

    }
}