CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    active BOOLEAN);

CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL);

CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE PRECISION,
    stock INTEGER,
    categoria_id BIGINT,
    CONSTRAINT fk_productos_categoria
    FOREIGN KEY (categoria_id) REFERENCES categorias (id)
    );

INSERT INTO users (name, email, active) VALUES
    ('Carlos Villalobos', 'carl@gmail.com', TRUE),
    ('Carolina Robles', 'caro@gmail.com', TRUE),
    ('Paco Fuentes', 'paco@gmail.com', FALSE);


INSERT INTO public.categorias (id, nombre) VALUES
    (1, 'Computadoras'),
    (2, 'Perifericos'),
    (3, 'Videovigilancia');

-- Inserts de ejemplo para productos
INSERT INTO public.productos (nombre, precio, stock, categoria_id) VALUES
    ('Laptop hp', 2500., 10, 1),
    ('Monitor', 300.99, 12, 1),
    ('teclado', 89.90, 30, 2),
    ('Camara dahua', 150.00, 5, 3);