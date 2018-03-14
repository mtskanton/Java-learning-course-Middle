INSERT INTO account VALUES (DEFAULT, 'Alex', 1000);
INSERT INTO account VALUES (DEFAULT, 'Billy', 1000);
INSERT INTO account VALUES (DEFAULT, 'Chris', 1000);

INSERT INTO users VALUES (DEFAULT, 'Axel', 'admin', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', DEFAULT);
INSERT INTO users VALUES (DEFAULT, 'Berg', 'user', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', DEFAULT);

INSERT INTO roles VALUES (DEFAULT, 'ROLE_ADMIN');
INSERT INTO roles VALUES (DEFAULT, 'ROLE_USER');

INSERT INTO users_roles VALUES (1, 1);
INSERT INTO users_roles VALUES (1, 2);
INSERT INTO users_roles VALUES (2, 2);

INSERT INTO regions VALUES (DEFAULT, 'Самарская область');
INSERT INTO regions VALUES (DEFAULT, 'Московская оласть');
INSERT INTO regions VALUES (DEFAULT, 'Ленинградская область');

INSERT INTO cities VALUES (DEFAULT, 'Самара', 1);
INSERT INTO cities VALUES (DEFAULT, 'Тольятти', 1);
INSERT INTO cities VALUES (DEFAULT, 'Жигулевск', 1);
INSERT INTO cities VALUES (DEFAULT, 'Москва', 2);
INSERT INTO cities VALUES (DEFAULT, 'Химки', 2);
INSERT INTO cities VALUES (DEFAULT, 'Санкт-Петербург', 3);
INSERT INTO cities VALUES (DEFAULT, 'Тосно', 3);

INSERT INTO companies VALUES (DEFAULT, 'Фокс', 1, 2, 'Мира-94');