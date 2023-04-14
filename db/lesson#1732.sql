create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type(name) values ('CHEESE'), ('MILK'), ('MILK PRODUCTS');
insert into product(name, type_id, expired_date, price) values ('ice cream', 3, '2023-04-15', 100.05), 
    ('vanilla ice cream', 3, '2023-04-13', 150.11),('ice cream max', 3, '2023-04-10', 195.5), 
    ('melted cheese', 1, '2023-04-13', 220), ('mozarella', 1, '2023-04-16', 300.1), 
    ('cow milk', 2, '2023-04-16', 111);

select p.name product, t.name type
from type t 
join product p 
on p.type_id = t.id
where t.name = 'CHEESE';

select * from product where name like '%ice cream%';

select * from product where expired_date < current_date;

select * from product where price = (select max(price) from product);

select t.name, count(*)
from type t 
join product p 
on p.type_id = t.id
group by t.name;

select p.name product, t.name type
from type t 
join product p 
on p.type_id = t.id
where t.name IN ('CHEESE', 'MILK');

select t.name, count(*)
from type t 
join product p 
on p.type_id = t.id
group by t.name
having count(*) < 10;

select p.name product, t.name type
from type t 
join product p 
on p.type_id = t.id;
