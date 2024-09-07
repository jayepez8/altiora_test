CREATE DATABASE IF NOT EXISTS altiora_test CHARACTER SET utf8;

USE altiora_test;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identification VARCHAR(10) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_code VARCHAR(10) NOT NULL UNIQUE,
    order_date DATE NOT NULL,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);

CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item_code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE order_items (
    order_id INT,
    item_id INT,
    quantity INT NOT NULL,
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE CASCADE
);

INSERT INTO
    customers (
        identification,
        first_name,
        last_name
    )
VALUES ('1234567890', 'John', 'Doe'),
    ('1234567891', 'Smith'),
    ('1234567892', 'Johnson');

INSERT INTO
    items (item_code, name, unit_price)
VALUES ('A001', 'Laptop', 1200.50),
    ('A002', 'Smartphone', 800.00),
    ('A003', 'Tablet', 450.75);

INSERT INTO
    orders (
        order_code,
        order_date,
        customer_id
    )
VALUES ('OC-000001', '2024-09-01', 1),
    ('OC-000002', '2024-09-03', 2),
    ('OC-000003', '2024-09-05', 1);

INSERT INTO
    order_items (order_id, item_id, quantity)
VALUES (1, 1, 2),
    (1, 3, 1),
    (2, 2, 1),
    (3, 2, 2),
    (3, 1, 1);