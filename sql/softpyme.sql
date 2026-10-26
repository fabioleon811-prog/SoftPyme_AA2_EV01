
CREATE DATABASE IF NOT EXISTS softpyme
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE softpyme;

CREATE TABLE IF NOT EXISTS registro_peso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(100) NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    fecha DATE NOT NULL,
    creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_peso_positivo CHECK (peso > 0)
);
