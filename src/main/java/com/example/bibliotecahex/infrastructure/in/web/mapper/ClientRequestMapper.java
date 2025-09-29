package com.example.bibliotecahex.infrastructure.in.web.mapper;

import com.example.bibliotecahex.application.command.CreateClientCommand;
import com.example.bibliotecahex.application.command.UpdateClientCommand;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.client.CreateClientRequest;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.client.UpdateClientRequest;

public class ClientRequestMapper {

    private ClientRequestMapper() {}

    public static CreateClientCommand toCommand(CreateClientRequest request) {
        return new CreateClientCommand(
                request.getNames(),
                request.getSurnames(),
                request.getDni(),
                request.getAge()
        );
    }

    public static UpdateClientCommand toCommand(UpdateClientRequest request) {
        return new UpdateClientCommand(
                request.getId(),
                request.getNames(),
                request.getSurnames(),
                request.getDni(),
                request.getAge()
        );
    }
}
