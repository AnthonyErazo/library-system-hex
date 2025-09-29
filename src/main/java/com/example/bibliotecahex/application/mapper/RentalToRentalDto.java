package com.example.bibliotecahex.application.mapper;

import com.example.bibliotecahex.application.dto.RentalDto;
import com.example.bibliotecahex.domain.model.entity.Rental;
import com.example.bibliotecahex.domain.model.entity.Order;
import com.example.bibliotecahex.domain.model.enums.RentalStatus;
import org.springframework.stereotype.Component;

@Component
public class RentalToRentalDto {
    
    public RentalDto buildRentalDtoFromOrder(Order order) {
        RentalDto dto = new RentalDto();
        dto.setId(order.getId());
        dto.setRequestId(order.getId());
        dto.setClientDni(order.getClient().getDni());
        dto.setBookId(order.getBooks().isEmpty() ? null : order.getBooks().get(0).getId());
        dto.setRequestDate(order.getOrderDate());
        dto.setProcessedDate(null);
        dto.setStatus(order.isStatus() ? RentalStatus.APPROVED : RentalStatus.PENDING);
        dto.setMessage(order.isStatus() ? "Rental approved" : "Rental pending");
        return dto;
    }
}