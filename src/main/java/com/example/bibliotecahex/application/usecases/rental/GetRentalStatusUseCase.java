package com.example.bibliotecahex.application.usecases.rental;

import com.example.bibliotecahex.application.dto.RentalDto;
import com.example.bibliotecahex.application.exception.BusinessException;
import com.example.bibliotecahex.application.mapper.RentalToRentalDto;
import com.example.bibliotecahex.domain.model.entity.Order;
import com.example.bibliotecahex.domain.port.out.persistent.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetRentalStatusUseCase {
    
    private final OrderRepository orderRepository;
    private final RentalToRentalDto mapper;

    public GetRentalStatusUseCase(OrderRepository orderRepository, RentalToRentalDto mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    public RentalDto execute(String requestId) {
        Optional<Order> orderOpt = orderRepository.findById(requestId);
        
        if (orderOpt.isEmpty()) {
            throw new BusinessException("Rental request not found with ID: " + requestId);
        }

        return mapper.buildRentalDtoFromOrder(orderOpt.get());
    }
}