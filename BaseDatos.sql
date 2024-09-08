CREATE DATABASE IF NOT EXISTS altiora_test CHARACTER SET utf8;

USE altiora_test;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identification VARCHAR(10) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    create_date DATE NOT NULL
);

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_code VARCHAR(10) NOT NULL UNIQUE,
    order_date DATE NOT NULL,
    total_order DECIMAL(10, 2) NOT NULL,
    create_date DATE NOT NULL,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);

CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    item_code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    create_date DATE NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE order_items (
    order_id INT,
    item_id INT,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    create_date DATE NOT NULL,
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE CASCADE
);

INSERT INTO
    customers (
        identification,
        first_name,
        last_name,
        create_date
    )
VALUES (
        '1234567890',
        'John',
        'Doe',
        '2024-09-01'
    ),
    (
        '0987654321',
        'Jane',
        'Smith',
        '2024-09-02'
    ),
    (
        '4567891234',
        'Alice',
        'Johnson',
        '2024-09-03'
    ),
    (
        '7891234567',
        'Bob',
        'Williams',
        '2024-09-04'
    ),
    (
        '1357924680',
        'Charlie',
        'Brown',
        '2024-09-05'
    ),
    (
        '2468013579',
        'David',
        'Jones',
        '2024-09-06'
    ),
    (
        '3692581470',
        'Eva',
        'Taylor',
        '2024-09-07'
    ),
    (
        '1112223334',
        'Frank',
        'Lee',
        '2024-09-08'
    ),
    (
        '4445556667',
        'Grace',
        'Harris',
        '2024-09-09'
    ),
    (
        '5556667778',
        'Henry',
        'Clark',
        '2024-09-10'
    ),
    (
        '2223334445',
        'Ivy',
        'Lewis',
        '2024-09-11'
    ),
    (
        '8889990001',
        'Jack',
        'Walker',
        '2024-09-12'
    ),
    (
        '3334445556',
        'Kate',
        'Hall',
        '2024-09-13'
    ),
    (
        '9990001112',
        'Leo',
        'Young',
        '2024-09-14'
    ),
    (
        '7778889993',
        'Mia',
        'King',
        '2024-09-15'
    ),
    (
        '6667778884',
        'Nina',
        'Scott',
        '2024-09-16'
    ),
    (
        '5556667775',
        'Oscar',
        'Green',
        '2024-09-17'
    ),
    (
        '4445556666',
        'Paul',
        'Baker',
        '2024-09-18'
    ),
    (
        '3334445557',
        'Quinn',
        'Adams',
        '2024-09-19'
    ),
    (
        '2223334448',
        'Rose',
        'Nelson',
        '2024-09-20'
    );

INSERT INTO
    items (
        item_code,
        name,
        create_date,
        unit_price
    )
VALUES (
        'ITEM0001',
        'Laptop',
        '2024-09-01',
        1200.00
    ),
    (
        'ITEM0002',
        'Smartphone',
        '2024-09-02',
        800.00
    ),
    (
        'ITEM0003',
        'Tablet',
        '2024-09-03',
        600.00
    ),
    (
        'ITEM0004',
        'Monitor',
        '2024-09-04',
        300.00
    ),
    (
        'ITEM0005',
        'Keyboard',
        '2024-09-05',
        50.00
    ),
    (
        'ITEM0006',
        'Mouse',
        '2024-09-06',
        25.00
    ),
    (
        'ITEM0007',
        'Printer',
        '2024-09-07',
        150.00
    ),
    (
        'ITEM0008',
        'Desk Chair',
        '2024-09-08',
        200.00
    ),
    (
        'ITEM0009',
        'Webcam',
        '2024-09-09',
        75.00
    ),
    (
        'ITEM0010',
        'Headphones',
        '2024-09-10',
        100.00
    ),
    (
        'ITEM0011',
        'Speaker',
        '2024-09-11',
        180.00
    ),
    (
        'ITEM0012',
        'External Hard Drive',
        '2024-09-12',
        90.00
    ),
    (
        'ITEM0013',
        'USB Hub',
        '2024-09-13',
        40.00
    ),
    (
        'ITEM0014',
        'Microphone',
        '2024-09-14',
        130.00
    ),
    (
        'ITEM0015',
        'Graphic Tablet',
        '2024-09-15',
        250.00
    ),
    (
        'ITEM0016',
        'Smart Watch',
        '2024-09-16',
        400.00
    ),
    (
        'ITEM0017',
        'Gaming Chair',
        '2024-09-17',
        350.00
    ),
    (
        'ITEM0018',
        'Projector',
        '2024-09-18',
        700.00
    ),
    (
        'ITEM0019',
        'Router',
        '2024-09-19',
        90.00
    ),
    (
        'ITEM0020',
        'Fitness Tracker',
        '2024-09-20',
        120.00
    );

INSERT INTO
    orders (
        order_code,
        order_date,
        total_order,
        create_date,
        customer_id
    )
VALUES (
        'OC-000001',
        '2024-09-01',
        1500.00,
        '2024-09-01',
        1
    ),
    (
        'OC-000002',
        '2024-09-02',
        850.00,
        '2024-09-02',
        2
    ),
    (
        'OC-000003',
        '2024-09-03',
        1800.00,
        '2024-09-03',
        3
    ),
    (
        'OC-000004',
        '2024-09-04',
        320.00,
        '2024-09-04',
        4
    ),
    (
        'OC-000005',
        '2024-09-05',
        1050.00,
        '2024-09-05',
        5
    ),
    (
        'OC-000006',
        '2024-09-06',
        275.00,
        '2024-09-06',
        6
    ),
    (
        'OC-000007',
        '2024-09-07',
        950.00,
        '2024-09-07',
        7
    ),
    (
        'OC-000008',
        '2024-09-08',
        2100.00,
        '2024-09-08',
        8
    ),
    (
        'OC-000009',
        '2024-09-09',
        120.00,
        '2024-09-09',
        9
    ),
    (
        'OC-000010',
        '2024-09-10',
        450.00,
        '2024-09-10',
        10
    ),
    (
        'OC-000011',
        '2024-09-11',
        920.00,
        '2024-09-11',
        11
    ),
    (
        'OC-000012',
        '2024-09-12',
        1100.00,
        '2024-09-12',
        12
    ),
    (
        'OC-000013',
        '2024-09-13',
        450.00,
        '2024-09-13',
        13
    ),
    (
        'OC-000014',
        '2024-09-14',
        1300.00,
        '2024-09-14',
        14
    ),
    (
        'OC-000015',
        '2024-09-15',
        750.00,
        '2024-09-15',
        15
    ),
    (
        'OC-000016',
        '2024-09-16',
        2400.00,
        '2024-09-16',
        16
    ),
    (
        'OC-000017',
        '2024-09-17',
        1900.00,
        '2024-09-17',
        17
    ),
    (
        'OC-000018',
        '2024-09-18',
        560.00,
        '2024-09-18',
        18
    ),
    (
        'OC-000019',
        '2024-09-19',
        210.00,
        '2024-09-19',
        19
    ),
    (
        'OC-000020',
        '2024-09-20',
        380.00,
        '2024-09-20',
        20
    );

INSERT INTO
    order_items (
        order_id,
        item_id,
        quantity,
        total_price,
        create_date
    )
VALUES (
        1,
        1,
        1,
        1200.00,
        '2024-09-01'
    ),
    (1, 5, 2, 100.00, '2024-09-01'),
    (2, 2, 1, 800.00, '2024-09-02'),
    (2, 6, 2, 50.00, '2024-09-02'),
    (
        3,
        3,
        2,
        1200.00,
        '2024-09-03'
    ),
    (3, 7, 1, 150.00, '2024-09-03'),
    (4, 8, 1, 200.00, '2024-09-04'),
    (4, 9, 1, 75.00, '2024-09-04'),
    (5, 4, 2, 600.00, '2024-09-05'),
    (
        5,
        10,
        3,
        150.00,
        '2024-09-05'
    ),
    (
        6,
        11,
        1,
        180.00,
        '2024-09-06'
    ),
    (6, 12, 1, 90.00, '2024-09-06'),
    (
        7,
        1,
        1,
        1200.00,
        '2024-09-07'
    ),
    (7, 13, 1, 40.00, '2024-09-07'),
    (
        8,
        14,
        2,
        260.00,
        '2024-09-08'
    ),
    (
        8,
        15,
        1,
        250.00,
        '2024-09-08'
    ),
    (
        9,
        16,
        1,
        400.00,
        '2024-09-09'
    ),
    (
        9,
        17,
        2,
        350.00,
        '2024-09-09'
    ),
    (
        10,
        18,
        1,
        700.00,
        '2024-09-10'
    ),
    (
        10,
        19,
        1,
        90.00,
        '2024-09-10'
    );