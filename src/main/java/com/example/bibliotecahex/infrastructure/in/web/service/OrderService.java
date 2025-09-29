package com.example.bibliotecahex.infrastructure.in.web.service;

import com.example.bibliotecahex.application.command.CreateOrderCommand;
import com.example.bibliotecahex.application.dto.OrderDto;
import com.example.bibliotecahex.application.usecases.order.CreateOrderUseCase;
import com.example.bibliotecahex.application.usecases.order.GetOrdersByClientDniUseCase;
import com.example.bibliotecahex.application.usecases.order.GetOrderByIdUseCase;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.order.CreateOrderRequest;
import com.example.bibliotecahex.infrastructure.in.web.dto.response.OrderResponse;
import com.example.bibliotecahex.infrastructure.in.web.mapper.OrderDtoMapper;
import com.example.bibliotecahex.infrastructure.in.web.mapper.OrderRequestMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrdersByClientDniUseCase getOrdersByClientDniUseCase;
    private final GetOrderByIdUseCase getOrderByIdUseCase;

    public OrderService(CreateOrderUseCase createOrderUseCase,
                       GetOrdersByClientDniUseCase getOrdersByClientDniUseCase,
                       GetOrderByIdUseCase getOrderByIdUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrdersByClientDniUseCase = getOrdersByClientDniUseCase;
        this.getOrderByIdUseCase = getOrderByIdUseCase;
    }

    public OrderResponse createOrder(CreateOrderRequest request) {
        CreateOrderCommand command = OrderRequestMapper.toCommand(request);
        OrderDto dto = createOrderUseCase.execute(command);
        return OrderDtoMapper.toResponse(dto);
    }

    public OrderResponse getOrderById(String id) {
        OrderDto dto = getOrderByIdUseCase.execute(id);
        return OrderDtoMapper.toResponse(dto);
    }

    public List<OrderResponse> getOrdersByClientDni(String dni) {
        List<OrderDto> dtos = getOrdersByClientDniUseCase.execute(dni);
        return OrderDtoMapper.toResponseList(dtos);
    }
}