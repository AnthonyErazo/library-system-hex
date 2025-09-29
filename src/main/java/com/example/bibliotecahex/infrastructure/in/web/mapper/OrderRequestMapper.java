package com.example.bibliotecahex.infrastructure.in.web.mapper;

import com.example.bibliotecahex.application.command.CreateOrderCommand;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.order.CreateOrderRequest;

public class OrderRequestMapper {

    private OrderRequestMapper() {}

    public static CreateOrderCommand toCommand(CreateOrderRequest request) {
        return new CreateOrderCommand(
                request.getClientId(),
                request.getBookIds()
        );
    }
}