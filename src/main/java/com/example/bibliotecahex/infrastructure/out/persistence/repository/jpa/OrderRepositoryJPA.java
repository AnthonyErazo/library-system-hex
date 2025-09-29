package com.example.bibliotecahex.infrastructure.out.persistence.repository.jpa;

import com.example.bibliotecahex.infrastructure.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepositoryJPA extends JpaRepository<OrderEntity, String> {
    
    List<OrderEntity> findByClientId(String clientId);
    
    @Query("SELECT o FROM OrderEntity o WHERE o.clientId IN (SELECT c.id FROM ClientEntity c WHERE c.dni = :dni)")
    List<OrderEntity> findByClientDni(@Param("dni") String dni);
}