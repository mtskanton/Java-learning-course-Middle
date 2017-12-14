-- скрипт для запуска из командной строки

create DATABASE items_storage;
\c items_storage


CREATE TABLE Roles (
id serial PRIMARY KEY,
title varchar (255) NOT NULL
);


CREATE TABLE Rules (
id serial PRIMARY KEY,
type varchar (255) NOT NULL
);


CREATE TABLE RoleRules (
id serial PRIMARY KEY,
role_id integer REFERENCES Roles(id) NOT NULL,
rule_id integer REFERENCES Rules(id) NOT NULL

);


CREATE TABLE Users (
id serial PRIMARY KEY,
login varchar (255) NOT NULL,
password varchar (255) NOT NULL,
name varchar (255) NOT NULL,
age varchar (255),
role_id integer REFERENCES Roles(id) NOT NULL
);


CREATE TABLE States (
id serial PRIMARY KEY,
type varchar (255) NOT NULL
);


CREATE TABLE Categories (
id serial PRIMARY KEY,
type varchar (255) NOT NULL
);


CREATE TABLE Items (
id serial PRIMARY KEY,
date TIMESTAMP NOT NULL DEFAULT now(),
text text NOT NULL,
user_id integer REFERENCES Users(id) NOT NULL,
category_id integer REFERENCES Categories(id) NOT NULL,
state_id integer REFERENCES States(id) NOT NULL
);

CREATE TABLE COMMENTS (
id serial PRIMARY KEY,
date TIMESTAMP NOT NULL DEFAULT now(),
text text NOT NULL,
item_id integer REFERENCES Items(id) NOT NULL
);

CREATE TABLE Attachments (
id serial PRIMARY KEY,
file text NOT NULL,
item_id integer REFERENCES Items(id) NOT NULL
);