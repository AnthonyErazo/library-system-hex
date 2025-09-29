package com.example.bibliotecahex.application.usecases.order;

import com.example.bibliotecahex.application.dto.OrderDto;
import com.example.bibliotecahex.application.mapper.OrderToOrderDto;
import com.example.bibliotecahex.domain.port.out.persistent.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetOrdersByClientDniUseCase {
    private final OrderRepository orderRepository;
    private final OrderToOrderDto mapper;

    public GetOrdersByClientDniUseCase(OrderRepository orderRepository, OrderToOrderDto mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    public List<OrderDto> execute(String dni) {
        return orderRepository.findByClientDni(dni)
                .stream()
                .map(mapper::buildOrderDto)
                .toList();
    }
}