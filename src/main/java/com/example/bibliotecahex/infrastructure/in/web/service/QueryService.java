package com.example.bibliotecahex.infrastructure.in.web.service;

import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.application.usecases.rental.GetRentedBooksByClientDniUseCase;
import com.example.bibliotecahex.infrastructure.in.web.dto.response.BookResponse;
import com.example.bibliotecahex.infrastructure.in.web.mapper.BookDtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {

    private final GetRentedBooksByClientDniUseCase getRentedBooksByClientDniUseCase;

    public QueryService(GetRentedBooksByClientDniUseCase getRentedBooksByClientDniUseCase) {
        this.getRentedBooksByClientDniUseCase = getRentedBooksByClientDniUseCase;
    }

    public List<BookResponse> getRentedBooksByClientDni(String clientDni) {
        List<BookDto> bookDtos = getRentedBooksByClientDniUseCase.execute(clientDni);
        return BookDtoMapper.toResponseList(bookDtos);
    }
}