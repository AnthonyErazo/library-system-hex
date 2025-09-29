package com.example.bibliotecahex.infrastructure.in.web.service;

import com.example.bibliotecahex.application.command.CreateClientCommand;
import com.example.bibliotecahex.application.command.UpdateClientCommand;
import com.example.bibliotecahex.application.dto.ClientDto;
import com.example.bibliotecahex.application.usecases.client.*;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.client.CreateClientRequest;
import com.example.bibliotecahex.infrastructure.in.web.dto.request.client.UpdateClientRequest;
import com.example.bibliotecahex.infrastructure.in.web.dto.response.ClientResponse;
import com.example.bibliotecahex.infrastructure.in.web.mapper.ClientDtoMapper;
import com.example.bibliotecahex.infrastructure.in.web.mapper.ClientRequestMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientService {

    private final CreateClientUseCase createClientUseCase;
    private final GetAllClientUsecase getAllClientUsecase;
    private final GetClientByIdUseCase getClientByIdUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;

    public ClientService(CreateClientUseCase createClientUseCase,
                           GetAllClientUsecase getAllClientUsecase,
                           GetClientByIdUseCase getClientByIdUseCase,
                           UpdateClientUseCase updateClientUseCase,
                           DeleteClientUseCase deleteClientUseCase) {
        this.createClientUseCase = createClientUseCase;
        this.getAllClientUsecase = getAllClientUsecase;
        this.getClientByIdUseCase = getClientByIdUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
    }

    public ClientResponse createClient(CreateClientRequest request){
        CreateClientCommand command = ClientRequestMapper.toCommand(request);
        ClientDto dto = createClientUseCase.execute(command);
        return ClientDtoMapper.toResponse(dto);
    }

    public ClientResponse updateClient(UpdateClientRequest request){
        UpdateClientCommand command = ClientRequestMapper.toCommand(request);
        ClientDto dto = updateClientUseCase.execute(command);
        return ClientDtoMapper.toResponse(dto);
    }

    public void deleteClient(String id){
        deleteClientUseCase.execute(id);
    }

    public ClientResponse getClient(String id){
        ClientDto dto = getClientByIdUseCase.execute(id);
        return ClientDtoMapper.toResponse(dto);
    }

    public List<ClientResponse> getAllClient(){
        List<ClientDto> dtos = getAllClientUsecase.execute();
        return ClientDtoMapper.toResponseList(dtos);
    }
}

