package com.example.bibliotecahex.application.usecases.client;

import com.example.bibliotecahex.application.dto.ClientDto;
import com.example.bibliotecahex.application.mapper.ClientToClientDto;
import com.example.bibliotecahex.domain.port.out.persistent.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllClientUsecase{

    private final ClientRepository clientRepository;
    private final ClientToClientDto mapper;

    public GetAllClientUsecase(ClientRepository clientRepository, ClientToClientDto mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public List<ClientDto> execute() {

        return clientRepository.findAll()
                .stream()
                .map(mapper::buildClientDto)
                .toList();

    }
}