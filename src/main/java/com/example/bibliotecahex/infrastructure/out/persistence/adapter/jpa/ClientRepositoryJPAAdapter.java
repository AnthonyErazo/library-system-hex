package com.example.bibliotecahex.infrastructure.out.persistence.adapter.jpa;


import org.springframework.stereotype.Component;
import com.example.bibliotecahex.domain.model.entity.Client;
import com.example.bibliotecahex.domain.port.out.persistent.ClientRepository;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.ClientEntity;
import com.example.bibliotecahex.infrastructure.out.persistence.mapper.ClientEntityMapper;
import com.example.bibliotecahex.infrastructure.out.persistence.repository.jpa.ClientRepositoryJPA;
import java.util.List;
import java.util.Optional;

@Component
public class ClientRepositoryJPAAdapter implements ClientRepository {

    private final ClientRepositoryJPA jpaRepository;
    private final ClientEntityMapper mapper;

    public ClientRepositoryJPAAdapter(ClientRepositoryJPA jpaRepository, ClientEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Client save(Client client) {
        ClientEntity entity = this.jpaRepository.save(mapper.toEntity(client));
        return mapper.toClient(entity);
    }

    @Override
    public Client update(Client client) {
        return save(client);
    }

    @Override
    public void delete(String id) {
        this.jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByDni(String dni) {
        return this.jpaRepository.existsByDni(dni);
    }

    @Override
    public Optional<Client> findById(String id) {
        return this.jpaRepository.findById(id)
                .map(mapper::toClient);
    }

    @Override
    public List<Client> findAll() {
        return this.jpaRepository.findAll()
                .stream()
                .map(mapper::toClient)
                .toList();
    }
}
