create table phoneNumber(
    id serial primary key,
    number varchar(20)
);

create table people(
    id serial primary key,
    name varchar(255),
    phoneNumber_id int references phoneNumber(id) unique
);

insert into phoneNumber(number) values ('+7(913)444-5656');
insert into people(name, phoneNumber_id) values ('Ivan', 1);
select * from people;
select * from phoneNumber where id in (select phoneNumber_id from people);