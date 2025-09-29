package com.example.bibliotecahex.application.mapper;

import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.application.dto.OrderDto;
import com.example.bibliotecahex.domain.model.entity.Book;
import com.example.bibliotecahex.domain.model.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderToOrderDto {
    private final ClientToClientDto clientMapper;

    public OrderToOrderDto(ClientToClientDto clientMapper) {
        this.clientMapper = clientMapper;
    }

    public OrderDto buildOrderDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setClient(clientMapper.buildClientDto(order.getClient()));
        dto.setStatus(order.isStatus());
        
        List<BookDto> bookDtos = order.getBooks().stream()
                .map(this::mapBookToDto)
                .toList();
        dto.setBooks(bookDtos);
        
        return dto;
    }

    private BookDto mapBookToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setStatus(book.getStatus());
        return dto;
    }
}