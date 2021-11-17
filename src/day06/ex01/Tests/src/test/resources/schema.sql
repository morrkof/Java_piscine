DROP table IF EXISTS Product;
CREATE table Product
(
    id   IDENTITY,
    name VARCHAR(128) NOT NULL,
    price INTEGER NOT NULL

);
