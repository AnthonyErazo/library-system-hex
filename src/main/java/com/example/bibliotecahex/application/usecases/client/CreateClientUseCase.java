package com.example.bibliotecahex.application.usecases.client;

import com.example.bibliotecahex.application.command.CreateClientCommand;
import com.example.bibliotecahex.application.dto.ClientDto;
import com.example.bibliotecahex.application.mapper.ClientToClientDto;
import com.example.bibliotecahex.domain.model.entity.Client;
import com.example.bibliotecahex.domain.model.exception.ClientInvalidException;
import com.example.bibliotecahex.domain.port.out.persistent.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateClientUseCase {
    private final ClientRepository clientRepository;
    private final ClientToClientDto mapper;

    public CreateClientUseCase(ClientRepository clientRepository, ClientToClientDto mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public ClientDto execute(CreateClientCommand cliente) {
        validateDniUnique(cliente.getDni());
        Client client = new Client(
                null,
                cliente.getNames(),
                cliente.getSurnames(),
                cliente.getDni(),
                cliente.getAge()
        );
        Client clientResult = clientRepository.save(client);
        return mapper.buildClientDto(clientResult);
    }

    private void validateDniUnique(String dni) {
        if (clientRepository.existsByDni(dni)) {
            throw new ClientInvalidException("DNI already exists");
        }
    }
}