-- bbdd/schema.sql
-- Esquema de base de datos de EncuestasQR
-- Responsable inicial: equipo de BBDD

CREATE TABLE alumnos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    curso VARCHAR(50) NOT NULL
);

CREATE TABLE profesores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    asignatura VARCHAR(100) NOT NULL
);

CREATE TABLE encuestas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    fecha_creacion DATE NOT NULL
);

-- TODO: tabla respuestas
-- TODO: tabla administradores
