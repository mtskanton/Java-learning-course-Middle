--Вывести все машины
SELECT car.model, t.name AS transmission, e.name AS engine, c.name AS carcase from car
LEFT OUTER JOIN Transmission AS t ON car.id_transmission = t.id
LEFT OUTER JOIN Engine AS e ON car.id_engine = e.id
LEFT OUTER JOIN Carcase AS c ON car.id_carcase = c.id;
;

--Вывести все неиспользуемые детали
SELECT t.name FROM car
RIGHT OUTER JOIN Transmission as t ON car.id_transmission = t.id
WHERE car.model IS NULL;

SELECT e.name FROM car
RIGHT OUTER JOIN Engine as e ON car.id_engine = e.id
WHERE car.model IS NULL;

SELECT c.name FROM car
RIGHT OUTER JOIN Carcase as c ON car.id_carcase = c.id
WHERE car.model IS NULL;
