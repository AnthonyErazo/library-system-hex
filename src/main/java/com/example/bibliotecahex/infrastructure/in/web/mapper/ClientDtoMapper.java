package com.example.bibliotecahex.infrastructure.in.web.mapper;

import com.example.bibliotecahex.application.dto.ClientDto;
import com.example.bibliotecahex.infrastructure.in.web.dto.response.ClientResponse;

import java.util.List;

public class ClientDtoMapper {

    private ClientDtoMapper() {}

    public static ClientResponse toResponse(ClientDto dto) {
        ClientResponse response = new ClientResponse();
        response.setId(dto.getId());
        response.setNames(dto.getNames());
        response.setSurnames(dto.getSurnames());
        response.setDni(dto.getDni());
        response.setAge(dto.getAge());
        return response;
    }

    public static List<ClientResponse> toResponseList(List<ClientDto> dtoList) {
        return dtoList.stream()
                .map(ClientDtoMapper::toResponse)
                .toList();
    }
}
