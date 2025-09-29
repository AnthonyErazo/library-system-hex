package com.example.bibliotecahex.application.usecases.rental;

import com.example.bibliotecahex.application.dto.BookDto;
import com.example.bibliotecahex.application.mapper.BookToBookDto;
import com.example.bibliotecahex.domain.model.entity.Order;
import com.example.bibliotecahex.domain.port.out.persistent.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRentedBooksByClientDniUseCase {
    
    private final OrderRepository orderRepository;
    private final BookToBookDto mapper;

    public GetRentedBooksByClientDniUseCase(OrderRepository orderRepository,
                                          BookToBookDto mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    public List<BookDto> execute(String clientDni) {
        List<Order> approvedOrders = orderRepository.findAll().stream()
                .filter(order -> order.getClient().getDni().equals(clientDni))
                .filter(Order::isStatus) // true = aprobado
                .toList();

        return approvedOrders.stream()
                .flatMap(order -> order.getBooks().stream())
                .map(mapper::buildBookDto)
                .toList();
    }
}