CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

INSERT INTO customers VALUES  (1, '���', '����', 15, '���'),
                              (2, '������', '����', 20, '�������'),
                              (3, '����', '����', 25, '��������'),
                              (4, '������', '����', 15, '������'),
                              (5, '�����', '�����', 35, '������');
                              
INSERT INTO orders VALUES (1, 1, 1),
                          (3, 30, 3),
                          (4, 25, 4);

SELECT * from customers where age = (SELECT min(age) from customers);
SELECT * from customers where id NOT IN (SELECT id from orders);