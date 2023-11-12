CREATE DATABASE heesinsa;
USE heesinsa;
CREATE TABLE products
(
    id          BINARY(16) PRIMARY KEY,
    name        VARCHAR(20) NOT NULL UNIQUE,
    category    VARCHAR(50) NOT NULL,
    price       bigint      NOT NULL,
    description VARCHAR(500) DEFAULT NULL,
    created_at  DATETIME(6) NOT NULL,
    updated_at  DATETIME(6) NOT NULL
);

CREATE TABLE orders
(
    id           BINARY(16) PRIMARY KEY,
    email        VARCHAR(50)  NOT NULL,
    address      VARCHAR(200) NOT NULL,
    postcode     VARCHAR(200) NOT NULL,
    order_status VARCHAR(50)  NOT NULL,
    created_at   DATETIME(6)  NOT NULL,
    updated_at   DATETIME(6)  NOT NULL
);

CREATE TABLE order_items
(
    seq        BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id   BINARY(16) NOT NULL,
    product_id BINARY(16) NOT NULL,
    quantity   INT        NOT NULL,
    INDEX (order_id),
    CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    CONSTRAINT product_id FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

DROP DATABASE heesinsa;
