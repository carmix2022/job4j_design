 create table books(
     id serial primary key,
     name varchar(255)
 );
 
 create table customers(
     id serial primary key,
     name varchar(255)
 );
 
 create table books_customers(
     id serial primary key,
     book_id int references books(id),
     customer_id int references customers(id)
 );

insert into customers(name) values ('Ivan');
insert into customers(name) values ('Kirill');
insert into customers(name) values ('Roman');

insert into books(name) values ('Война и мир');
insert into books(name) values ('Что делать?');
insert into books(name) values ('Колобок');

insert into books_customers(book_id, customer_id) values (1, 1);
insert into books_customers(book_id, customer_id) values (1, 2);
insert into books_customers(book_id, customer_id) values (1, 3);
insert into books_customers(book_id, customer_id) values (2, 1);
insert into books_customers(book_id, customer_id) values (2, 2);
insert into books_customers(book_id, customer_id) values (3, 3);
