package com.example.bibliotecahex.infrastructure.in.web.mapper;

import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.infrastructure.in.web.dto.response.BookResponse;

import java.util.List;

public class BookDtoMapper {

    private BookDtoMapper() {}

    public static BookResponse toResponse(BookDto dto) {
        BookResponse response = new BookResponse();
        response.setId(dto.getId());
        response.setName(dto.getName());
        response.setAuthor(dto.getAuthor());
        response.setIsbn(dto.getIsbn());
        response.setStatus(dto.getStatus());
        return response;
    }

    public static List<BookResponse> toResponseList(List<BookDto> dtoList) {
        return dtoList.stream()
                .map(BookDtoMapper::toResponse)
                .toList();
    }
}