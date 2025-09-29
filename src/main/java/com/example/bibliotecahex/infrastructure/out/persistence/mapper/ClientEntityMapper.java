package com.example.bibliotecahex.infrastructure.out.persistence.mapper;

import com.example.bibliotecahex.domain.model.entity.Client;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientEntityMapper {

    public ClientEntity toEntity(Client dto) {
        ClientEntity entity = new ClientEntity();
        entity.setId(dto.getId());
        entity.setNames(dto.getNames());
        entity.setSurnames(dto.getSurnames());
        entity.setDni(dto.getDni());
        entity.setAge(dto.getAge());
        return entity;
    }

    public Client toClient(ClientEntity entity) {
        Client merchant = new Client(
                null,
                entity.getNames(),
                entity.getSurnames(),
                entity.getDni(),
                entity.getAge()
        );
        merchant.setId(entity.getId());
        return merchant;
    }
}