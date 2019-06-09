DELETE FROM USER_ROLES;
DELETE FROM VOTES;
DELETE FROM DISHES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (id, name, email, login, password) VALUES
('1', 'Admin', 'addmin@mail.com', 'admin', 'asd'),
('2', 'User', 'usser@mail.com', 'user', 'asd'),
('100', 'Admin', 'admin@mail.com', 'adminn', 'admin'),
('101', 'User', 'user@mail.com', 'userr', 'user'),
('102', 'Alex', 'alex@mail.com', 'alex', 'alex'),
('103', 'John_Doe', 'johndoe@mail.com', 'john', 'doe'),
('104', 'Jane_Doe', 'Jane_Doe@mail.com', 'jane', 'doe');

INSERT INTO user_roles (user_id, role) VALUES
('1', 'ROLE_ADMIN'),
('100', 'ROLE_ADMIN'),
('2', 'ROLE_USER'),
('100', 'ROLE_USER'),
('101', 'ROLE_USER'),
('102', 'ROLE_USER'),
('103', 'ROLE_USER'),
('104', 'ROLE_USER');

INSERT INTO restaurants (id, name) VALUES
('105', 'Стардог!s'),
('106', 'Вилка-ложка'),
('107', 'Жар-пицца'),
('108', 'Крошка картошка'),
('109', 'Му-му');

INSERT INTO dishes (id, name, price, date, restaurant_id) VALUES
('110', 'Французский хот-дог', '100', CURRENT_DATE, 105),
('111', 'Датский хот-дог', '120', CURRENT_DATE, 105),
('112', 'Бифбургер', '150', CURRENT_DATE, 105),
('113', 'Рулле', '99', CURRENT_DATE + INTERVAL '-1 day', 105),
('114', 'Салат Цезарь', '70', CURRENT_DATE, 106),
('115', 'Борщ', '50', CURRENT_DATE, 106),
('116', 'Солянка домашняя', '65', CURRENT_DATE, 106),
('117', 'Свинина по-московски', '120', CURRENT_DATE + INTERVAL '-1 day', 106),
('118', 'Пицца Баварская', '550', CURRENT_DATE, 107),
('119', 'Пицца Пепперони', '520', CURRENT_DATE, 107),
('120', 'Куриные ножки', '265', CURRENT_DATE + INTERVAL '-1 day', 107),
('121', 'Стрипсы', '180', CURRENT_DATE + INTERVAL '-1 day', 107),
('122', 'Сырники', '50', CURRENT_DATE, 108),
('123', 'Клубенёк по-французски', '70', CURRENT_DATE, 108),
('124', 'Краб Ролл', '90', CURRENT_DATE, 108),
('125', 'Потато Дог', '100', CURRENT_DATE + INTERVAL '-1 day', 108),
('126', 'Салат мясной', '100', CURRENT_DATE, 109),
('127', 'Лобио', '90', CURRENT_DATE, 109),
('128', 'Лапша куриная', '110', CURRENT_DATE, 109),
('129', 'Харчо', '110', CURRENT_DATE + INTERVAL '-1 day', 109);


INSERT INTO votes (id, user_id, restaurant_id, date, time) VALUES
('130', '101', '105', CURRENT_DATE, '09:15:00'),
('131', '101', '108', CURRENT_DATE, '09:25:00'),
('132', '101', '109', CURRENT_DATE + INTERVAL '-1 day', '10:15:00'),
('133', '101', '107', CURRENT_DATE + INTERVAL '-1 day', '10:30:00'),
('134', '102', '106', CURRENT_DATE, '08:50:00'),
('135', '102', '107', CURRENT_DATE, '09:45:00'),
('136', '102', '109', CURRENT_DATE, '10:20:00'),
('137', '102', '108', CURRENT_DATE, '10:35:00'),
('138', '102', '107', CURRENT_DATE + INTERVAL '-1 day', '07:10:00'),
('139', '103', '105', CURRENT_DATE, '10:05:00'),
('140', '103', '108', CURRENT_DATE, '10:35:00'),
('141', '103', '106', CURRENT_DATE + INTERVAL '-1 day', '07:50:00'),
('142', '104', '107', CURRENT_DATE, '07:40:00'),
('143', '104', '108', CURRENT_DATE, '08:50:00'),
('144', '104', '109', CURRENT_DATE, '10:55:00'),
('145', '104', '109', CURRENT_DATE + INTERVAL '-1 day', '07:10:00'),
('146', '100', '105', CURRENT_DATE, '10:15:00');