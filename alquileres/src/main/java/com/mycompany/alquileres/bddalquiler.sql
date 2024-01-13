GRANT ALL PRIVILEGES ON bddalquiler.* TO 'root'@'localhost';
SHOW DATABASES;

FLUSH PRIVILEGES;
CREATE TABLE Alquiler (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_entrada DATE,
    tiempo_estimado_alquiler INT,
    cliente_id INT,
    cliente_datos_facturacion INT,
    vivienda_id INT,
    vivienda_metros_cuadrados INT,
    vivienda_habitaciones INT,
    vivienda_banos INT,
    vivienda_precio_mensual INT,
    vivienda_codigo INT
);

CREATE TABLE Cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    datos_facturacion INT
);

CREATE TABLE Vivienda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ubicacion VARCHAR(100),
    metros_cuadrados INT,
    habitaciones INT,
    banos INT,
    precio_mensual INT,
    codigo INT
);

-- Agregar claves foráneas para mantener las relaciones entre las tablas
ALTER TABLE Alquiler ADD FOREIGN KEY (cliente_id) REFERENCES Cliente(id);
ALTER TABLE Alquiler ADD FOREIGN KEY (vivienda_id) REFERENCES Vivienda(id);