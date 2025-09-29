package com.example.bibliotecahex.domain.port.out.persistent;

import com.example.bibliotecahex.domain.model.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(String id);
    List<Order> findByClientId(String clientId);
    List<Order> findByClientDni(String dni);
    List<Order> findAll();
}