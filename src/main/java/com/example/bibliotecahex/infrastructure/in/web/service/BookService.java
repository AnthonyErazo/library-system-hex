package com.example.bibliotecahex.infrastructure.in.web.service;

import com.example.bibliotecahex.application.command.CreateBookCommand;
import com.example.bibliotecahex.application.command.UpdateBookCommand;
import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.application.usecases.book.*;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.book.CreateBookRequest;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.book.UpdateBookRequest;
import com.example.bibliotecahex.infrastructure.in.web.dto.response.BookResponse;
import com.example.bibliotecahex.infrastructure.in.web.mapper.BookDtoMapper;
import com.example.bibliotecahex.infrastructure.in.web.mapper.BookRequestMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookService {

    private final CreateBookUseCase createBookUseCase;
    private final GetAllBookUsecase getAllBookUsecase;
    private final GetBookByIdUseCase getBookByIdUseCase;
    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;

    public BookService(CreateBookUseCase createBookUseCase,
                       GetAllBookUsecase getAllBookUsecase,
                       GetBookByIdUseCase getBookByIdUseCase,
                       UpdateBookUseCase updateBookUseCase,
                       DeleteBookUseCase deleteBookUseCase) {
        this.createBookUseCase = createBookUseCase;
        this.getAllBookUsecase = getAllBookUsecase;
        this.getBookByIdUseCase = getBookByIdUseCase;
        this.updateBookUseCase = updateBookUseCase;
        this.deleteBookUseCase = deleteBookUseCase;
    }

    public BookResponse createBook(CreateBookRequest request){
        CreateBookCommand command = BookRequestMapper.toCommand(request);
        BookDto dto = createBookUseCase.execute(command);
        return BookDtoMapper.toResponse(dto);
    }

    public BookResponse updateBook(UpdateBookRequest request){
        UpdateBookCommand command = BookRequestMapper.toCommand(request);
        BookDto dto = updateBookUseCase.execute(command);
        return BookDtoMapper.toResponse(dto);
    }

    public void deleteBook(String id){
        deleteBookUseCase.execute(id);
    }

    public BookResponse getBook(String id){
        BookDto dto = getBookByIdUseCase.execute(id);
        return BookDtoMapper.toResponse(dto);
    }

    public List<BookResponse> getAllBooks(){
        List<BookDto> dtos = getAllBookUsecase.execute();
        return BookDtoMapper.toResponseList(dtos);
    }
}

