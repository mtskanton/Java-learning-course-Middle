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
    email VARCHAR(100) UNIQUE,
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

DROP TABLE IF EXISTS persistent_logins;

CREATE TABLE IF NOT EXISTS persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS regions;

CREATE TABLE IF NOT EXISTS regions (
    id SERIAL PRIMARY KEY,
    region VARCHAR (100)
);

CREATE TABLE IF NOT EXISTS cities (
    id SERIAL PRIMARY KEY,
    city VARCHAR (100),
    region_id INT NOT NULL,
    CONSTRAINT cities_region_id_fk FOREIGN KEY (region_id) REFERENCES regions (id)
);

CREATE TABLE IF NOT EXISTS companies (
    id SERIAL PRIMARY KEY,
    title VARCHAR (100),
    region_id INT NOT NULL,
    city_id INT NOT NULL,
    address VARCHAR (255),
    CONSTRAINT companies_region_id_fk FOREIGN KEY (region_id) REFERENCES regions (id),
    CONSTRAINT companies_cities_id_fk FOREIGN KEY (city_id) REFERENCES cities (id)
);

