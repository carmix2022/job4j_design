create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('Department 1');
insert into departments(name) values ('Department 2');
insert into departments(name) values ('Department 3');
insert into departments(name) values ('Department 4');

insert into employees(name, department_id) values ('Vasya', 1);
insert into employees(name, department_id) values ('Petya', 2);
insert into employees(name, department_id) values ('Katya', 3);
insert into employees(name, department_id) values ('Olga', 1);
insert into employees(name, department_id) values ('Liza', 2);
insert into employees(name, department_id) values ('Stas', 3);

select * from employees left join departments d on department_id = d.id;
select * from employees right join departments d on department_id = d.id;
select * from employees full join departments d on department_id = d.id;
select * from employees cross join departments;

select * from departments d left join employees e on d.id = e.department_id where e.department_id is null;

select e.name, d.name from employees e left join departments d on e.department_id = d.id;
select e.name, d.name from departments d right join employees e on e.department_id = d.id;

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values ('Olga', 'W'), ('Vasya', 'M'), ('Sasha', 'M'), ('Katya', 'W');
select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender != t2.gender;

