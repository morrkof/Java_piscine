DROP table IF EXISTS USERS;
CREATE table USERS
(
    ID    serial not null primary key,
    EMAIL VARCHAR(500)
);


