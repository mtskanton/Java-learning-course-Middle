--скрипт заполнения созданных таблиц

INSERT INTO Transmission (name) VALUES ('mechanic');
INSERT INTO Transmission (name) VALUES ('robot');
INSERT INTO Transmission (name) VALUES ('automatic');

INSERT INTO Engine (name) VALUES ('2 valves');
INSERT INTO Engine (name) VALUES ('4 valves');
INSERT INTO Engine (name) VALUES ('6 valves');
INSERT INTO Engine (name) VALUES ('8 valves');

INSERT INTO Carcase (name) VALUES ('sedan');
INSERT INTO Carcase (name) VALUES ('universal');
INSERT INTO Carcase (name) VALUES ('hatchback');
INSERT INTO Carcase (name) VALUES ('pickup');
INSERT INTO Carcase (name) VALUES ('SUV');

INSERT INTO Car (model, id_transmission, id_engine, id_carcase) VALUES ('Lada 001', 1, 2, 5);
INSERT INTO Car (model, id_transmission, id_engine, id_carcase) VALUES ('Lada 002', 3, 2, 3);
INSERT INTO Car (model, id_transmission, id_engine, id_carcase) VALUES ('Lada 003', 1, 4, 1);
INSERT INTO Car (model, id_transmission, id_engine, id_carcase) VALUES ('Lada 004', 3, 4, 3);
INSERT INTO Car (model, id_transmission, id_engine, id_carcase) VALUES ('Lada 005', 1, 2, 5);