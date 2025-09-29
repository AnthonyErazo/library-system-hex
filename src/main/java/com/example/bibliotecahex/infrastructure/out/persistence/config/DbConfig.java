package com.example.bibliotecahex.infrastructure.out.persistence.config;

import com.example.bibliotecahex.infrastructure.out.persistence.exception.DatabaseException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DbConfig {
    private DbConfig() { }

    private static final String DB_USER = System.getenv().getOrDefault("DB_USER", "sa");
    private static final String DB_PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "");
    private static final String URL = "jdbc:h2:mem:biblioteca;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_LOWER=TRUE;CASE_INSENSITIVE_IDENTIFIERS=TRUE";

    private static DataSource dataSource;

    public static void setDataSource(DataSource ds) {
        dataSource = ds;
    }

    static {
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS cliente (
                    id VARCHAR(50) PRIMARY KEY,
                    nombres VARCHAR(255) NOT NULL,
                    apellidos VARCHAR(255) NOT NULL,
                    dni CHAR(8) NOT NULL UNIQUE,
                    edad INTEGER NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS libro (
                    id VARCHAR(50) PRIMARY KEY,
                    nombre VARCHAR(255) NOT NULL,
                    autor VARCHAR(255) NOT NULL,
                    isbn CHAR(13) NOT NULL UNIQUE,
                    estado BOOLEAN NOT NULL
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pedidos (
                    id VARCHAR(50) PRIMARY KEY,
                    fecha_pedido TIMESTAMP NOT NULL,
                    cliente_id VARCHAR(50) NOT NULL,
                    estado BOOLEAN DEFAULT TRUE,
                    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS pedido_items (
                    id VARCHAR(50) PRIMARY KEY,
                    pedido_id VARCHAR(50) NOT NULL,
                    libro_id VARCHAR(50) NOT NULL,
                    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
                    FOREIGN KEY (libro_id) REFERENCES libro(id)
                )
            """);

        } catch (Exception e) {
            throw new DatabaseException("Error initializing database schema: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource != null) {
            return DataSourceUtils.getConnection(dataSource);
        }
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }
}
