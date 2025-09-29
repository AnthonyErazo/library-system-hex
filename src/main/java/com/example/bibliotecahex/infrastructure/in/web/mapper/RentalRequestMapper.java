package com.example.bibliotecahex.infrastructure.in.web.mapper;

import com.example.bibliotecahex.application.command.RequestRentalCommand;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.rental.RequestRentalRequest;
import org.springframework.stereotype.Component;

@Component
public class RentalRequestMapper {

    public RequestRentalCommand toCommand(RequestRentalRequest request) {
        return new RequestRentalCommand(request.getClientDni(), request.getBookId());
    }
}