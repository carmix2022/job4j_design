create table car_bodies(
    id serial primary key,
    name varchar(255)
);

create table car_engines(
    id serial primary key,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key,
    name varchar(255)
);

create table cars(
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('sedan'), ('hatchback'), ('pickup'), ('bus');
insert into car_engines(name) values ('max'), ('med'), ('min');
insert into car_transmissions(name) values ('pro'), ('normal'), ('simple');
insert into cars(name, body_id, engine_id, transmission_id) 
values 
    ('toyota', 2, 1, 2), ('jeep', null, 1, 1),
    ('mers', 2, null, 1), ('dodge', 3, 1, null),
    ('bmw', 2, 1, 1), ('daewoo', 3, 3, 3);

select c.id, c.name car_name, b.name body_name, e.name engine_name, t.name transmission_name 
from cars c
left join car_bodies b
on c.body_id = b.id
left join car_engines e
on c.engine_id = e.id
left join car_transmissions t
on c.transmission_id = t.id;

select * from car_bodies b
left join cars c
on c.body_id = b.id
where c.body_id is null;

select * from car_engines e
left join cars c
on c.engine_id = e.id
where c.engine_id is null;

select * from car_transmissions t
left join cars c
on c.transmission_id = t.id
where c.transmission_id is null;