package com.example.bibliotecahex.infrastructure.out.persistence.repository.jpa;

import com.example.bibliotecahex.infrastructure.out.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepositoryJPA extends JpaRepository<OrderItemEntity, String> {
    List<OrderItemEntity> findByOrderId(String orderId);
    void deleteByOrderId(String orderId);
}