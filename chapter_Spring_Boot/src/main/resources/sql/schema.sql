DROP TABLE IF EXISTS account;

CREATE TABLE IF NOT EXISTS account (
    id SERIAL,
    name VARCHAR(100),
    balance REAL NOT NULL,
    CONSTRAINT account_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL,
    name VARCHAR(100),
    login VARCHAR(100) UNIQUE,
    password VARCHAR(128),
    enabled BOOLEAN DEFAULT TRUE,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles (
    id SERIAL,
    role VARCHAR(50),
    CONSTRAINT roles_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    CONSTRAINT users_roles_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT users_roles_roles_id_fk FOREIGN KEY (role_id) REFERENCES roles (id)
);



