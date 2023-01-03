CREATE TABLE IF NOT EXISTS localidad (
  id int NOT NULL,
  altura float DEFAULT NULL,
  latitud float DEFAULT NULL,
  longitud float DEFAULT NULL,
  nombre varchar(120) DEFAULT NULL,
  poblacion int DEFAULT NULL,
  provincia varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;