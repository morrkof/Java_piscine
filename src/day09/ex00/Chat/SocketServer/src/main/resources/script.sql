DROP table IF EXISTS users;
CREATE table users
(
    ID       serial       not null primary key,
    USERNAME VARCHAR(1000) not null unique,
    PASSWORD VARCHAR(1000)
);

INSERT INTO users (username, password)
values ('user1', 'qwerty'),
       ('user2', '12345'),
       ('user3', 'qwe'),
       ('user4', 'password'),
       ('user5', 'kqrh7w4!sf');