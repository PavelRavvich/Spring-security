--CREATE DATABASE spring_security;

CREATE TABLE users (
  id       SERIAL      NOT NULL,
  username VARCHAR(20) UNIQUE NOT NULL,
  password VARCHAR(20) UNIQUE NOT NULL,
  enabled  BOOLEAN     NOT NULL DEFAULT FALSE,
  PRIMARY KEY (username)
);

CREATE TABLE user_roles (
  user_role_id SERIAL PRIMARY KEY,
  username     VARCHAR(20) NOT NULL,
  role         VARCHAR(20) NOT NULL,
  UNIQUE (username, role),
  FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO users (id, username, password, enabled) VALUES (DEFAULT ,'jack', 'jack', TRUE);
INSERT INTO users (id, username, password, enabled) VALUES (DEFAULT ,'peter', 'peter', TRUE);

INSERT INTO user_roles (username, role) VALUES ('jack', 'ROLE_USER');
INSERT INTO user_roles (username, role) VALUES ('jack', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role) VALUES ('peter', 'ROLE_USER');