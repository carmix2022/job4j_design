 create table pupils(
     id serial primary key,
     name varchar(255)
 );
 
 create table courses(
     id serial primary key,
     name varchar(255)
 );
 
 create table pupils_courses(
     id serial primary key,
     pupil_id int references pupils(id),
     course_id int references courses(id)
 );

insert into pupils(name) values ('Ivan');
insert into pupils(name) values ('Kirill');
insert into pupils(name) values ('Roman');

insert into courses(name) values ('Java SE');
insert into courses(name) values ('Spring');
insert into courses(name) values ('Hibernate');

insert into pupils_courses(pupil_id, course_id) values (1, 1);
insert into pupils_courses(pupil_id, course_id) values (1, 2);
insert into pupils_courses(pupil_id, course_id) values (1, 3);
insert into pupils_courses(pupil_id, course_id) values (2, 1);
insert into pupils_courses(pupil_id, course_id) values (2, 2);
insert into pupils_courses(pupil_id, course_id) values (3, 3);

select pp.name, c.name from pupils as pp
join pupils_courses as pc on pc.pupil_id = pp.id
join courses as c on pc.course_id = c.id;

select pp.name as name, c.name as course from pupils as pp
join pupils_courses as pc on pc.pupil_id = pp.id
join courses as c on pc.course_id = c.id;

select pp.name as "student's name", c.name course from pupils as pp
join pupils_courses as pc on pc.pupil_id = pp.id
join courses as c on pc.course_id = c.id;


