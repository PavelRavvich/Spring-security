CREATE DATABASE spring_security;

CREATE TABLE IF NOT EXISTS users (
  id       SERIAL PRIMARY KEY,
  username VARCHAR(20),
  password VARCHAR(20),
  role     VARCHAR(5)
);