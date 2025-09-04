CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    active BOOLEAN);

INSERT INTO users (name, email, active) VALUES
    ('Carlos Villalobos', 'carl@gmail.com', TRUE),
    ('Carolina Robles', 'caro@gmail.com', TRUE),
    ('Paco Fuentes', 'paco@gmail.com', FALSE);