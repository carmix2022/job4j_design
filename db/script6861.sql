create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('tiger', 20000,'500-01-01');
insert into fauna(name, avg_age, discovery_date) values ('elephant', 30000, null);
insert into fauna(name, avg_age, discovery_date) values ('fish', 2000,'100-01-01');

select * from fauna where name like '%fish%';
select * from fauna where avg_age >= 10000 and avg_age <= 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
