DROP TABLE IF EXISTS account;

CREATE TABLE IF NOT EXISTS account (
    id SERIAL,
    name VARCHAR(100),
    balance REAL NOT NULL,
    CONSTRAINT account_pk PRIMARY KEY (id)
);