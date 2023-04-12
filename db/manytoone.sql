create table groupsSport(
     id serial primary key,
     name varchar(255)
 );

 create table students(
     id serial primary key,
     name varchar(255)
	 groupSport_id int references groupsSport(id)
 );

insert into groupsSport(name) values ('swimming');
insert into students(name, groupSport_id) values ('Ivan', 1);

select * from students;

select * from groupsSport where id in (select groupSport_id from students);
