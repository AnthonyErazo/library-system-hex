package com.example.bibliotecahex.domain.port.out.persistent;

import com.example.bibliotecahex.domain.model.entity.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client merchant);
    Client update(Client merchant);
    void delete(String id);
    boolean existsByDni(String email);
    Optional<Client> findById(String merchantId);
    List<Client> findAll();

}
