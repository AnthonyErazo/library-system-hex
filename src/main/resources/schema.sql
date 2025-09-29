-- Tabla clientes
CREATE TABLE IF NOT EXISTS cliente (
    id VARCHAR(36) PRIMARY KEY,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    dni VARCHAR(8) UNIQUE NOT NULL,
    edad INTEGER NOT NULL
);

-- Tabla libros
CREATE TABLE IF NOT EXISTS libro (
    id VARCHAR(36) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    isbn VARCHAR(13) UNIQUE NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla pedidos (orders)
CREATE TABLE IF NOT EXISTS pedidos (
    id VARCHAR(36) PRIMARY KEY,
    fecha_pedido TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    cliente_id VARCHAR(36) NOT NULL,
    estado BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Tabla items de pedidos
CREATE TABLE IF NOT EXISTS pedido_items (
    id VARCHAR(36) PRIMARY KEY,
    pedido_id VARCHAR(36) NOT NULL,
    libro_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (libro_id) REFERENCES libro(id)
);