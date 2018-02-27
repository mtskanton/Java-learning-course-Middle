DROP TABLE IF EXISTS advertisements;
DROP TABLE IF EXISTS carcases;
DROP TABLE IF EXISTS brands;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    login VARCHAR(50),
    password VARCHAR(50),
    phone BIGINT,
    enabled  BOOLEAN
);

CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS users_roles (
    id_user INT,
    id_role INT,
    CONSTRAINT fk_ur_user FOREIGN KEY (id_user) REFERENCES users (id),
    CONSTRAINT fk_ur_role FOREIGN KEY (id_role) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS carcases (
    id SERIAL PRIMARY KEY,
    type VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS brands (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS advertisements (
    id SERIAL PRIMARY KEY,
    brand_id INTEGER,
    model VARCHAR(100),
    description TEXT,
    manufacture_year CHAR(4),
    carcase_id INTEGER,
    price INTEGER,
    publish_date TIMESTAMP without time zone DEFAULT now(),
    sold BOOLEAN DEFAULT false,
    user_id INTEGER,
    CONSTRAINT fk_adv_brands FOREIGN KEY (brand_id) REFERENCES brands(id),
    CONSTRAINT fk_adv_carcase FOREIGN KEY (carcase_id) REFERENCES carcases(id),
    CONSTRAINT fk_adv_users FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users VALUES (DEFAULT, 'Anton', 'a', 'a', 89271111111, 'true');
INSERT INTO users VALUES (DEFAULT, 'Boris', 'b', 'b', 89272222222, 'true');
INSERT INTO users VALUES (DEFAULT, 'Chris', 'c', 'c', 89272222222, 'false');

INSERT INTO roles VALUES (DEFAULT, 'ROLE_USER');
INSERT INTO roles VALUES (DEFAULT, 'ROLE_ADMIN');

INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (1, 2);
INSERT INTO users_roles VALUES (2, 1);

INSERT INTO carcases VALUES (DEFAULT, 'седан');
INSERT INTO carcases VALUES (DEFAULT, 'хэтчбек');
INSERT INTO carcases VALUES (DEFAULT, 'универсал');
INSERT INTO carcases VALUES (DEFAULT, 'внедорожник');

INSERT INTO brands VALUES (DEFAULT, 'Ford');
INSERT INTO brands VALUES (DEFAULT, 'Volkswagen');
INSERT INTO brands VALUES (DEFAULT, 'Kia');
INSERT INTO brands VALUES (DEFAULT, 'Mazda');

INSERT INTO advertisements VALUES (DEFAULT, 1, 'Focus', 'не бит, не крашен', 2016, 1, 1000000, DEFAULT, DEFAULT, 1);
INSERT INTO advertisements VALUES (DEFAULT, 2, 'Polo', 'еще быстрее', 2016, 2, 1200000, DEFAULT, DEFAULT, 2);
INSERT INTO advertisements VALUES (DEFAULT, 1, 'Fiesta', 'лучший выбор', 2014, 1, 800000, DEFAULT, DEFAULT, 2);
INSERT INTO advertisements VALUES (DEFAULT, 3, 'Rio', 'торг', 2001, 2, 560000, DEFAULT, DEFAULT, 1);