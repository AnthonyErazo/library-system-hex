package com.example.bibliotecahex.infrastructure.out.persistence.adapter.jdbc;

import com.example.bibliotecahex.infrastructure.out.persistence.repository.jdbc.OrderRepositoryJDBC;
import com.example.bibliotecahex.infrastructure.out.persistence.mapper.OrderEntityMapper;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.OrderEntity;
import com.example.bibliotecahex.domain.port.out.persistent.OrderRepository;
import com.example.bibliotecahex.domain.model.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderRepositoryJDBCAdapter implements OrderRepository {

    private final OrderRepositoryJDBC orderRepositoryJDBC;
    private final OrderEntityMapper mapper;

    public OrderRepositoryJDBCAdapter(OrderRepositoryJDBC orderRepositoryJDBC, OrderEntityMapper mapper) {
        this.orderRepositoryJDBC = orderRepositoryJDBC;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = this.orderRepositoryJDBC.save(mapper.toEntity(order));
        return mapper.toOrder(entity);
    }

    @Override
    public Optional<Order> findById(String id) {
        return this.orderRepositoryJDBC.findById(id)
                .map(mapper::toOrder);
    }

    @Override
    public List<Order> findByClientId(String clientId) {
        return this.orderRepositoryJDBC.findByClientId(clientId)
                .stream()
                .map(mapper::toOrder)
                .toList();
    }

    @Override
    public List<Order> findByClientDni(String dni) {
        return this.orderRepositoryJDBC.findByClientDni(dni)
                .stream()
                .map(mapper::toOrder)
                .toList();
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepositoryJDBC.findAll()
                .stream()
                .map(mapper::toOrder)
                .toList();
    }
}