package com.example.bibliotecahex.application.usecases.book;

import com.example.bibliotecahex.application.command.UpdateBookCommand;
import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.application.exception.BusinessException;
import com.example.bibliotecahex.application.mapper.BookToBookDto;
import com.example.bibliotecahex.domain.model.entity.Book;
import com.example.bibliotecahex.domain.port.out.persistent.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBookUseCase{

    private final BookRepository bookRepository;
    private final BookToBookDto mapper;

    public UpdateBookUseCase(BookRepository bookRepository, BookToBookDto mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public BookDto execute(UpdateBookCommand bookCmd) {

        Optional<Book> optionalBook = bookRepository.findById(bookCmd.getId());

        if (optionalBook.isEmpty()) {
            throw new BusinessException("Book not found");
        }

        Book book = optionalBook.get();

        if (bookCmd.getName() != null && !bookCmd.getName().isEmpty()) {
            book.setName(bookCmd.getName());
        }

        if (bookCmd.getAuthor() != null && !bookCmd.getAuthor().isEmpty()) {
            book.setAuthor(bookCmd.getAuthor());
        }

        if (bookCmd.getIsbn() != null && !bookCmd.getIsbn().isEmpty()) {
            book.setIsbn(bookCmd.getIsbn());
        }

        if (bookCmd.getStatus() != null) {
            book.setStatus(bookCmd.getStatus());
        }

        Book bookUpdated  = bookRepository.update(book);

        return mapper.buildBookDto(bookUpdated);
    }
}