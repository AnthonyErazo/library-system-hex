package com.example.bibliotecahex.infrastructure.out.persistence.repository.jpa;

import com.example.bibliotecahex.infrastructure.out.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepositoryJPA extends JpaRepository<ClientEntity, String> {

    Optional<ClientEntity> findByDni(String dni);
    boolean existsByDni(String dni);
}
