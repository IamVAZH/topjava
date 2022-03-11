DELETE
FROM user_roles;
DELETE
FROM meals;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (user_id, datetime, description, calories)
VALUES ('100000', '2021-01-29 11:00:00', 'Завтрак', '400'),
       ('100000', '2021-01-30 15:02:11', 'Обед', '1200'),
       ('100000', '2021-01-30 17:11:00', 'Ужин', '900'),
       ('100000', '2021-06-01 09:00:10', 'Завтрак', '450'),
       ('100000', '2021-09-11 10:00:00', 'Завтрак', '500'),
       ('100000', '2021-01-22 11:02:22', 'Завтрак', '80'),
       ('100001', '2021-03-02 12:23:40', 'Админ Завтрак', '400'),
       ('100001', '2021-03-02 18:00:00', 'Админ Обед', '2100'),
       ('100001', '2021-04-22 23:00:01', 'Админ Ужин', '1500');