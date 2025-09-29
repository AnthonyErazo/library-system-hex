package com.example.bibliotecahex.infrastructure.in.web.mapper;

import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.application.dto.OrderDto;
import com.example.bibliotecahex.infrastructure.in.web.dto.response.BookResponse;
import com.example.bibliotecahex.infrastructure.in.web.dto.response.OrderResponse;

import java.util.List;

public class OrderDtoMapper {

    private OrderDtoMapper() {}

    public static OrderResponse toResponse(OrderDto dto) {
        OrderResponse response = new OrderResponse();
        response.setId(dto.getId());
        response.setOrderDate(dto.getOrderDate());
        response.setClient(ClientDtoMapper.toResponse(dto.getClient()));
        response.setStatus(dto.getStatus());
        
        List<BookResponse> bookResponses = dto.getBooks().stream()
                .map(OrderDtoMapper::mapBookDtoToResponse)
                .toList();
        response.setBooks(bookResponses);
        
        return response;
    }

    public static List<OrderResponse> toResponseList(List<OrderDto> dtoList) {
        return dtoList.stream()
                .map(OrderDtoMapper::toResponse)
                .toList();
    }

    private static BookResponse mapBookDtoToResponse(BookDto dto) {
        BookResponse response = new BookResponse();
        response.setId(dto.getId());
        response.setName(dto.getName());
        response.setAuthor(dto.getAuthor());
        response.setIsbn(dto.getIsbn());
        response.setStatus(dto.getStatus());
        return response;
    }
}