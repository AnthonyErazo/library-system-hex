package com.example.bibliotecahex.infrastructure.out.persistence.adapter.jpa;

import com.example.bibliotecahex.domain.model.entity.Order;
import com.example.bibliotecahex.domain.port.out.persistent.OrderRepository;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.OrderEntity;
import com.example.bibliotecahex.infrastructure.out.persistence.mapper.OrderEntityMapper;
import com.example.bibliotecahex.infrastructure.out.persistence.repository.jpa.OrderRepositoryJPA;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

//@Component  // DESACTIVADO - USANDO JDBC
public class OrderRepositoryJPAAdapter implements OrderRepository {

    private final OrderRepositoryJPA jpaRepository;
    private final OrderEntityMapper mapper;

    public OrderRepositoryJPAAdapter(OrderRepositoryJPA jpaRepository, OrderEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = this.jpaRepository.save(mapper.toEntity(order));
        return mapper.toOrder(entity);
    }

    @Override
    public Optional<Order> findById(String id) {
        return this.jpaRepository.findById(id)
                .map(mapper::toOrder);
    }

    @Override
    public List<Order> findByClientId(String clientId) {
        return this.jpaRepository.findByClientId(clientId)
                .stream()
                .map(mapper::toOrder)
                .toList();
    }

    @Override
    public List<Order> findByClientDni(String dni) {
        return this.jpaRepository.findByClientDni(dni)
                .stream()
                .map(mapper::toOrder)
                .toList();
    }

    @Override
    public List<Order> findAll() {
        return this.jpaRepository.findAll()
                .stream()
                .map(mapper::toOrder)
                .toList();
    }
}