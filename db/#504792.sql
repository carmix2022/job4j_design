create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('Ivan Ivanov');
insert into students (name) values ('Petr Petrov');

create table teachers (
    id serial primary key,
    name varchar(50)
);

insert into teachers (name) values ('Landau');
insert into teachers (name) values ('Kapitsa');

create table courses (
    id serial primary key,
    name varchar(200),
    teacher_id integer references teachers(id)
);

insert into courses (name, teacher_id) values ('Fizika tverdogo tela', 1);
insert into courses (name, teacher_id) values ('Fizika sverkhprovodnikov', 1);
insert into courses (name, teacher_id) values ('Kvantovaya fizika', 1);
insert into courses (name, teacher_id) values ('Molekularnaya fizika', 2);
insert into courses (name, teacher_id) values ('Volokonnaya optika', 2);

create table courses_students (
    id serial primary key,
    course_id integer references courses(id),
    student_id integer references students(id)
);

insert into courses_students (course_id, student_id) values (1, 1);
insert into courses_students (course_id, student_id) values (3, 1);
insert into courses_students (course_id, student_id) values (5, 2);
insert into courses_students (course_id, student_id) values (4, 1);
insert into courses_students (course_id, student_id) values (2, 2);


select * from students;
select * from teachers;
select * from courses;
select * from courses_students;

create view show_students_with_2_or_more_courses
 as select s.name as student, count(t.name), t.name from students as s
     join courses_students cs on s.id = cs.student_id
     join courses c on cs.course_id = c.id
     join teachers t on c.teacher_id = t.id
     group by (s.name, t.name) having count(t.name) >= 2;

select * from show_students_with_2_or_more_courses;