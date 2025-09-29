package com.example.bibliotecahex.application.mapper;

import org.springframework.stereotype.Component;

import com.example.bibliotecahex.application.dto.ClientDto;
import com.example.bibliotecahex.domain.model.entity.Client;

@Component
public class ClientToClientDto {

    public ClientDto buildClientDto(Client client) {
        ClientDto response = new ClientDto();
        response.setId(client.getId());
        response.setNames(client.getNames());
        response.setSurnames(client.getSurnames());
        response.setDni(client.getDni());
        response.setAge(client.getAge());

        return response;
    }
}