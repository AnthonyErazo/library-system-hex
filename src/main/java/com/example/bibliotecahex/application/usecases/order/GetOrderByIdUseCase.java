package com.example.bibliotecahex.application.usecases.order;

import com.example.bibliotecahex.application.dto.OrderDto;
import com.example.bibliotecahex.application.exception.BusinessException;
import com.example.bibliotecahex.application.mapper.OrderToOrderDto;
import com.example.bibliotecahex.domain.model.entity.Order;
import com.example.bibliotecahex.domain.port.out.persistent.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetOrderByIdUseCase {
    private final OrderRepository orderRepository;
    private final OrderToOrderDto mapper;

    public GetOrderByIdUseCase(OrderRepository orderRepository, OrderToOrderDto mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    public OrderDto execute(String id) {
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isEmpty()) {
            throw new BusinessException("Order not found with id: " + id);
        }
        return mapper.buildOrderDto(orderOpt.get());
    }
}