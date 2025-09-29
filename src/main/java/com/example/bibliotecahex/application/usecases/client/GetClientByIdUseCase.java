package com.example.bibliotecahex.application.usecases.client;

import com.example.bibliotecahex.application.dto.ClientDto;
import com.example.bibliotecahex.application.exception.BusinessException;
import com.example.bibliotecahex.application.mapper.ClientToClientDto;
import com.example.bibliotecahex.domain.model.entity.Client;
import com.example.bibliotecahex.domain.port.out.persistent.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetClientByIdUseCase{

    private final ClientRepository clientRepository;
    private final ClientToClientDto mapper;

    public GetClientByIdUseCase(ClientRepository clientRepository, ClientToClientDto mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public ClientDto execute(String clientId) {

        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isEmpty()) {
            throw new BusinessException("Client not found");
        }

        return mapper.buildClientDto(optionalClient.get());

    }
}