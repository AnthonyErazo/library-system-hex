package com.example.bibliotecahex.infrastructure.out.persistence.repository.jdbc;

import com.example.bibliotecahex.infrastructure.out.persistence.config.DbConfig;
import com.example.bibliotecahex.infrastructure.out.persistence.entity.BookEntity;
import com.example.bibliotecahex.infrastructure.out.persistence.exception.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryJDBC {
    public BookEntity save(BookEntity bookEntity) {
        String sql = "INSERT INTO libro (id, nombre, autor, isbn, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bookEntity.getId());
            ps.setString(2, bookEntity.getName());
            ps.setString(3, bookEntity.getAuthor());
            ps.setString(4, bookEntity.getIsbn());
            ps.setBoolean(5, bookEntity.getStatus());

            ps.executeUpdate();
            return bookEntity;

        } catch (Exception ex) {
            throw new DatabaseException("Error saving book: " + ex.getMessage());
        }
    }

    public BookEntity update(BookEntity bookEntity) {
        String sql = "UPDATE libro SET nombre = ?, autor = ?, isbn = ?, estado = ? WHERE id = ?";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bookEntity.getName());
            ps.setString(2, bookEntity.getAuthor());
            ps.setString(3, bookEntity.getIsbn());
            ps.setBoolean(4, bookEntity.getStatus());
            ps.setString(5, bookEntity.getId());

            ps.executeUpdate();
            return bookEntity;

        } catch (Exception ex) {
            throw new DatabaseException("Error updating book: " + ex.getMessage());
        }
    }

    public boolean existsByIsbn(String isbn) {
        String sql = "SELECT 1 FROM libro WHERE isbn = ? LIMIT 1";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, isbn);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (Exception ex) {
            throw new DatabaseException("Error checking ISBN existence: " + ex.getMessage());
        }
    }

    public boolean existsById(String id) {
        String sql = "SELECT 1 FROM libro WHERE id = ? LIMIT 1";

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

    public Optional<BookEntity> findById(String id) {
        String sql = "SELECT * FROM libro WHERE id = ?";

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
            throw new DatabaseException("Error finding book by ID: " + ex.getMessage());
        }
    }

    public List<BookEntity> findAll() {
        String sql = "SELECT * FROM libro";
        List<BookEntity> books = new ArrayList<>();

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                books.add(mapResultSetToEntity(rs));
            }
            return books;

        } catch (Exception ex) {
            throw new DatabaseException("Error listing books: " + ex.getMessage());
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM libro WHERE id = ?";

        try (Connection con = DbConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception ex) {
            throw new DatabaseException("Error deleting book: " + ex.getMessage());
        }
    }

    private BookEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        BookEntity entity = new BookEntity();
        entity.setId(rs.getString("id"));
        entity.setName(rs.getString("nombre"));
        entity.setAuthor(rs.getString("autor"));
        entity.setIsbn(rs.getString("isbn"));
        entity.setStatus(rs.getBoolean("estado"));
        return entity;
    }
}
