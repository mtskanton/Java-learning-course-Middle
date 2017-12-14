-- скрипт заполнения созданных таблиц

INSERT INTO Rules (type) VALUES ('create');
INSERT INTO Rules (type) VALUES ('read');
INSERT INTO Rules (type) VALUES ('update');
INSERT INTO Rules (type) VALUES ('delete');

INSERT INTO Roles (title) VALUES ('user');
INSERT INTO Roles (title) VALUES ('admin');

INSERT INTO RoleRules (role_id, rule_id) VALUES (1, 1);
INSERT INTO RoleRules (role_id, rule_id) VALUES (1, 2);
INSERT INTO RoleRules (role_id, rule_id) VALUES (2, 1);
INSERT INTO RoleRules (role_id, rule_id) VALUES (2, 2);
INSERT INTO RoleRules (role_id, rule_id) VALUES (2, 3);
INSERT INTO RoleRules (role_id, rule_id) VALUES (2, 4);

INSERT INTO Users (login, password, name, age, role_id) VALUES ('admin', 'admin', 'Anton', 30, 2);

INSERT INTO States (type) VALUES ('new');
INSERT INTO States (type) VALUES ('closed');

INSERT INTO Categories (type) VALUES ('standart');
INSERT INTO Categories (type) VALUES ('urgent');

INSERT INTO Items (text, user_id, category_id, state_id) VALUES ('common text of the item', 1, 1, 2);

INSERT INTO Attachments (file, item_id) VALUES ('D:\test.txt', 1);

INSERT INTO Comments (text, item_id) VALUES ('simple comment', 1);