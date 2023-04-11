create table birthday(		
	id serial primary key,	
	name text,	
	day date,
	age integer
);
insert into birthday(name, day, age) values('Иван', '2000-01-01', 23);
update birthday set name = 'Петр';
delete from birthday;