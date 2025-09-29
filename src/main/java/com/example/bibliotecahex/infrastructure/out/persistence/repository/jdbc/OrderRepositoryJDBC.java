package com.example.bibliotecahex.infrastructure.out.persistence.repository.jdbc;

import com.example.bibliotecahex.infrastructure.out.persistence.config.DbConfig;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.OrderEntity;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.OrderItemEntity;
import com.example.bibliotecahex.infrastructure.out.persistence.exception.DatabaseException;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderRepositoryJDBC {

    public OrderEntity save(OrderEntity orderEntity) {
        // Verificar si la orden ya existe
        Optional<OrderEntity> existingOrder = findById(orderEntity.getId());
        
        if (existingOrder.isPresent()) {
            // UPDATE - La orden ya existe
            String updateSql = "UPDATE pedidos SET fecha_pedido = ?, cliente_id = ?, estado = ? WHERE id = ?";
            
            try (Connection con = DbConfig.getConnection();
                 PreparedStatement ps = con.prepareStatement(updateSql)) {

                ps.setTimestamp(1, Timestamp.valueOf(orderEntity.getOrderDate()));
                ps.setString(2, orderEntity.getClientId());
                ps.setBoolean(3, orderEntity.getStatus());
                ps.setString(4, orderEntity.getId());

                ps.executeUpdate();

                return orderEntity;

            } catch (Exception ex) {
                throw new DatabaseException("Error updating order: " + ex.getMessage());
            }
        } else {
            String insertSql = "INSERT INTO pedidos (id, fecha_pedido, cliente_id, estado) VALUES (?, ?, ?, ?)";

            try (Connection con = DbConfig.getConnection();
                 PreparedStatement ps = con.prepareStatement(insertSql)) {

                ps.setString(1, orderEntity.getId());
                ps.setTimestamp(2, Timestamp.valueOf(orderEntity.getOrderDate()));
                ps.setString(3, orderEntity.getClientId());
                ps.setBoolean(4, orderEntity.getStatus());

                ps.executeUpdate();

                if (orderEntity.getOrderItems() != null) {
                    for (OrderItemEntity item : orderEntity.getOrderItems()) {
                        String itemSql = "INSERT INTO pedido_items (id, pedido_id, libro_id) VALUES (?, ?, ?)";
                        try (PreparedStatement itemPs = con.prepareStatement(itemSql)) {
                            itemPs.setString(1, java.util.UUID.randomUUID().toString());
                            itemPs.setString(2, orderEntity.getId());
                            itemPs.setString(3, item.getBookId());
                            itemPs.executeUpdate();
                        }
                    }
                }

                return orderEntity;

            } catch (Exception ex) {
                throw new DatabaseException("Error saving order: " + ex.getMessage());
            }
        }
    }

    public Optional<OrderEntity> findById(String id) {
        String sql = """
            SELECT DISTINCT 
                p.id, p.fecha_pedido, p.cliente_id, p.estado
            FROM pedidos p
            WHERE p.id = ?
            """;

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    OrderEntity entity = mapResultSetToEntity(rs);
                    entity.setOrderItems(findOrderItems(id));
                    return Optional.of(entity);
                } else {
                    return Optional.empty();
                }
            }

        } catch (Exception ex) {
            throw new DatabaseException("Error finding order by ID: " + ex.getMessage());
        }
    }

    public List<OrderEntity> findByClientId(String clientId) {
        String sql = """
            SELECT DISTINCT 
                p.id, p.fecha_pedido, p.cliente_id, p.estado
            FROM pedidos p
            WHERE p.cliente_id = ?
            """;

        List<OrderEntity> orders = new ArrayList<>();

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, clientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderEntity entity = mapResultSetToEntity(rs);
                    entity.setOrderItems(findOrderItems(entity.getId()));
                    orders.add(entity);
                }
            }
            return orders;

        } catch (Exception ex) {
            throw new DatabaseException("Error finding orders by client ID: " + ex.getMessage());
        }
    }

    public List<OrderEntity> findByClientDni(String dni) {
        String sql = """
            SELECT DISTINCT 
                p.id, p.fecha_pedido, p.cliente_id, p.estado
            FROM pedidos p
            JOIN cliente c ON p.cliente_id = c.id
            WHERE c.dni = ?
            """;

        List<OrderEntity> orders = new ArrayList<>();

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderEntity entity = mapResultSetToEntity(rs);
                    entity.setOrderItems(findOrderItems(entity.getId()));
                    orders.add(entity);
                }
            }
            return orders;

        } catch (Exception ex) {
            throw new DatabaseException("Error finding orders by client DNI: " + ex.getMessage());
        }
    }

    public List<OrderEntity> findAll() {
        String sql = """
            SELECT DISTINCT 
                p.id, p.fecha_pedido, p.cliente_id, p.estado
            FROM pedidos p
            ORDER BY p.id
            """;

        List<OrderEntity> orders = new ArrayList<>();

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                OrderEntity entity = mapResultSetToEntity(rs);
                entity.setOrderItems(findOrderItems(entity.getId()));
                orders.add(entity);
            }
            return orders;

        } catch (Exception ex) {
            throw new DatabaseException("Error listing orders: " + ex.getMessage());
        }
    }

    private List<OrderItemEntity> findOrderItems(String orderId) {
        String sql = "SELECT id, pedido_id, libro_id FROM pedido_items WHERE pedido_id = ?";
        List<OrderItemEntity> items = new ArrayList<>();

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderItemEntity item = new OrderItemEntity();
                    item.setId(rs.getString("id"));
                    item.setOrderId(rs.getString("pedido_id"));
                    item.setBookId(rs.getString("libro_id"));
                    items.add(item);
                }
            }
            return items;

        } catch (Exception ex) {
            throw new DatabaseException("Error finding order items: " + ex.getMessage());
        }
    }

    private OrderEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        OrderEntity entity = new OrderEntity();
        entity.setId(rs.getString("id"));
        entity.setOrderDate(rs.getTimestamp("fecha_pedido").toLocalDateTime());
        entity.setClientId(rs.getString("cliente_id"));
        entity.setStatus(rs.getBoolean("estado"));
        return entity;
    }
}