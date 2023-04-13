create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('phone', 100.5), ('pad', 250.33), ('player', 120.01);
insert into people(name) values ('Vanya'), ('Petya'), ('Vasya');
insert into devices_people(device_id, people_id) values (1, 1), (1, 3);
insert into devices_people(device_id, people_id) values (2, 2), (2, 3);
insert into devices_people(device_id, people_id) values (3, 1), (3, 2), (3, 3);


select avg(price) from devices;

select avg(d.price) 
from devices d
join devices_people dp 
on dp.device_id = d.id 
join people p 
on dp.people_id = p.id;

select p.name, avg(d.price) 
from people p
join devices_people dp 
on dp.device_id = p.id 
join devices d 
on dp.people_id = d.id
group by p.name;

select p.name, avg(d.price) 
from people p
join devices_people dp 
on dp.device_id = p.id 
join devices d 
on dp.people_id = d.id
group by p.name
having avg(d.price) > 150;




