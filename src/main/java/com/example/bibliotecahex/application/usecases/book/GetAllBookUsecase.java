package com.example.bibliotecahex.application.usecases.book;

import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.application.mapper.BookToBookDto;
import com.example.bibliotecahex.domain.port.out.persistent.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllBookUsecase{

    private final BookRepository bookRepository;
    private final BookToBookDto mapper; 

    public GetAllBookUsecase(BookRepository bookRepository, BookToBookDto mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public List<BookDto> execute() {

        return bookRepository.findAll()
                .stream()
                .map(mapper::buildBookDto)
                .toList();

    }
}