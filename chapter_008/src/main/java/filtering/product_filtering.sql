
--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT p.name, p.expired_date, p.price, t.name FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE t.name = 'СЫР';


--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product WHERE name LIKE '%мороженное%';


--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце
SELECT * FROM product WHERE expired_date BETWEEN '2018-01-01' AND '2018-02-01';


--4. Написать запрос, который вывод самый дорогой продукт
SELECT * FROM product ORDER BY price DESC LIMIT 1;


--5. Написать запрос, который выводит количество всех продуктов определенного типа
SELECT COUNT(*) FROM product WHERE type_id = 1;


--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.name, p.expired_date, p.price, t.name FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE t.name = 'СЫР' OR t.name = 'МОЛОКО';


--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук
SELECT t.name FROM product AS p
INNER JOIN type AS t ON p.type_id = t.id
WHERE p.amount < 10;


--8. Вывести все продукты и их тип
SELECT p.name, t.name FROM product AS p
INNER JOIN type AS t on p.type_id = t.id;