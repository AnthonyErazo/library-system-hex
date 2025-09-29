package com.example.bibliotecahex.infrastructure.out.persistence.adapter.jdbc;

import com.example.bibliotecahex.infrastructure.out.persistence.repository.jdbc.ClientRepositoryJDBC;
import com.example.bibliotecahex.infrastructure.out.persistence.mapper.ClientEntityMapper;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.ClientEntity;
import com.example.bibliotecahex.domain.port.out.persistent.ClientRepository;
import com.example.bibliotecahex.domain.model.entity.Client;

import java.util.List;
import java.util.Optional;

public class ClientRepositoryJDBCAdapter implements ClientRepository {

    private final ClientRepositoryJDBC clientRepositoryJDBC;
    private final ClientEntityMapper mapper;

    public ClientRepositoryJDBCAdapter(ClientRepositoryJDBC clientRepositoryJDBC, ClientEntityMapper mapper) {
        this.clientRepositoryJDBC = clientRepositoryJDBC;
        this.mapper = mapper;
    }

    @Override
    public Client save(Client client) {
        ClientEntity entity = this.clientRepositoryJDBC.save(mapper.toEntity(client));
        return mapper.toClient(entity);
    }

    @Override
    public Client update(Client client) {
        ClientEntity entity = this.clientRepositoryJDBC.update(mapper.toEntity(client));
        return mapper.toClient(entity);
    }

    @Override
    public void delete(String id) {
        this.clientRepositoryJDBC.delete(id);
    }

    @Override
    public boolean existsByDni(String email) {
        return this.clientRepositoryJDBC.existsByDni(email);
    }

    @Override
    public Optional<Client> findById(String merchantId) {
        return this.clientRepositoryJDBC.findById(merchantId)
                .map(mapper::toClient);
    }

    @Override
    public List<Client> findAll() {
        return this.clientRepositoryJDBC.findAll()
                .stream()
                .map(mapper::toClient)
                .toList();
    }
}