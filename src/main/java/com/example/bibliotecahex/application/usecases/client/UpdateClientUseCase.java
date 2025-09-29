package com.example.bibliotecahex.application.usecases.client;

import com.example.bibliotecahex.application.command.UpdateClientCommand;
import com.example.bibliotecahex.application.dto.ClientDto;
import com.example.bibliotecahex.application.exception.BusinessException;
import com.example.bibliotecahex.application.mapper.ClientToClientDto;
import com.example.bibliotecahex.domain.model.entity.Client;
import com.example.bibliotecahex.domain.port.out.persistent.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateClientUseCase{

    private final ClientRepository clientRepository;
    private final ClientToClientDto mapper;

    public UpdateClientUseCase(ClientRepository clientRepository, ClientToClientDto mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public ClientDto execute(UpdateClientCommand clientCmd) {

        Optional<Client> optionalClient = clientRepository.findById(clientCmd.getId());

        if (optionalClient.isEmpty()) {
            throw new BusinessException("Client not found");
        }

        Client client  = optionalClient.get();

        if (clientCmd.getNames() != null && !clientCmd.getNames().isEmpty()) {

            client.setNames(clientCmd.getNames());
        }

        if (clientCmd.getSurnames() != null && !clientCmd.getSurnames().isEmpty()) {
            client.setSurnames(clientCmd.getSurnames());
        }

        if (clientCmd.getDni() != null && !clientCmd.getDni().isEmpty()) {
            client.setDni(clientCmd.getDni());
        }

        if (clientCmd.getAge() != null && clientCmd.getAge() > 0) {
            client.setAge(clientCmd.getAge());
        }

        Client clientUpdated  = clientRepository.update(client);

        return mapper.buildClientDto(clientUpdated);

    }
}