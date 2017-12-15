--скрипт создания базы данных и таблиц

CREATE DATABASE cars_db;
\c cars_db;

CREATE TABLE Transmission (
	id SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL
);

CREATE TABLE Engine (
	id SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL
);

CREATE TABLE Carcase (
	id SERIAL PRIMARY KEY,
	name varchar(255) NOT NULL
);

CREATE TABLE car (
	id SERIAL PRIMARY KEY,
	model varchar(255),
	id_transmission int REFERENCES Transmission(id),
	id_engine int REFERENCES Engine(id),
	id_carcase int REFERENCES Carcase(id)
);