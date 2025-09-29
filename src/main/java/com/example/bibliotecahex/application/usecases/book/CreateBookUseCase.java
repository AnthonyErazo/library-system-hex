package com.example.bibliotecahex.application.usecases.book;

import com.example.bibliotecahex.application.command.CreateBookCommand;
import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.application.mapper.BookToBookDto;
import com.example.bibliotecahex.domain.model.entity.Book;
import com.example.bibliotecahex.domain.model.exception.BookInvalidException;
import com.example.bibliotecahex.domain.port.out.persistent.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateBookUseCase {
    private final BookRepository bookRepository;
    private final BookToBookDto mapper;

    public CreateBookUseCase(BookRepository bookRepository, BookToBookDto mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public BookDto execute(CreateBookCommand bookCommand) {
        validateIsbnUnique(bookCommand.getIsbn());

        Book book = new Book(
                null,
                bookCommand.getName(),
                bookCommand.getAuthor(),
                bookCommand.getIsbn(),
                bookCommand.getStatus()
        );
        Book bookResult = bookRepository.save(book);
        return mapper.buildBookDto(bookResult);
    }

    private void validateIsbnUnique(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw new BookInvalidException("ISBN already exists");
        }
    }
}