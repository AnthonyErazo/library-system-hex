package com.example.bibliotecahex.infrastructure.out.persistence.repository.jdbc;

import com.example.bibliotecahex.infrastructure.out.persistence.config.DbConfig;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.ClientEntity;
import com.example.bibliotecahex.infrastructure.out.persistence.exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepositoryJDBC {
    public ClientEntity save(ClientEntity clientEntity) {
        String sql = "INSERT INTO cliente (id, nombres, apellidos, dni, edad) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, clientEntity.getId());
            ps.setString(2, clientEntity.getNames());
            ps.setString(3, clientEntity.getSurnames());
            ps.setString(4, clientEntity.getDni());
            ps.setInt(5, clientEntity.getAge());

            ps.executeUpdate();
            return clientEntity;

        } catch (Exception ex) {
            throw new DatabaseException("Error saving client: " + ex.getMessage());
        }
    }

    public ClientEntity update(ClientEntity clientEntity) {
        String sql = "UPDATE cliente SET nombres = ?, apellidos = ?, dni = ?, edad = ? WHERE id = ?";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, clientEntity.getNames());
            ps.setString(2, clientEntity.getSurnames());
            ps.setString(3, clientEntity.getDni());
            ps.setInt(4, clientEntity.getAge());

            ps.executeUpdate();
            return clientEntity;

        } catch (Exception ex) {
            throw new DatabaseException("Error updating client: " + ex.getMessage());
        }
    }

    public boolean existsByDni(String dni) {
        String sql = "SELECT 1 FROM cliente WHERE dni = ? LIMIT 1";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception ex) {
            throw new DatabaseException("Error checking dni existence: " + ex.getMessage());
        }
    }

    public boolean existsById(String id) {
        String sql = "SELECT 1 FROM cliente WHERE id = ? LIMIT 1";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception ex) {
            throw new DatabaseException("Error checking ID existence: " + ex.getMessage());
        }
    }

    public Optional<ClientEntity> findById(String id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapResultSetToEntity(rs));
                } else {
                    return Optional.empty();
                }
            }

        } catch (Exception ex) {
            throw new DatabaseException("Error finding client by ID: " + ex.getMessage());
        }
    }

    public List<ClientEntity> findAll() {
        String sql = "SELECT * FROM cliente";
        List<ClientEntity> clients = new ArrayList<>();

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clients.add(mapResultSetToEntity(rs));
            }
            return clients;

        } catch (Exception ex) {
            throw new DatabaseException("Error listing clients: " + ex.getMessage());
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception ex) {
            throw new DatabaseException("Error deleting client: " + ex.getMessage());
        }
    }

    private ClientEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        ClientEntity entity = new ClientEntity();
        entity.setId(rs.getString("id"));
        entity.setNames(rs.getString("nombres"));
        entity.setSurnames(rs.getString("apellidos"));
        entity.setDni(rs.getString("dni"));
        entity.setAge(rs.getInt("edad"));
        return entity;
    }
}
