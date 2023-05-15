CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values (1, 'Facebook'), (2, 'Apple'), (3, 'Amazon'), (4, 'Netflix'), (5, 'Google');
insert into person (id, name, company_id) values (1, 'Vasya', 1), (2, 'Petya', 2), (3, 'Kolya', 3), (4, 'Zhenya', 4),
    (5, 'Dima', 5), (6, 'Sasha', 5), (7, 'Senya', 5), (8, 'Ilya', 2), (9, 'Kirya', 2), (10, 'Serega', 1);

select p.name, c.name as "company" from person as p join company as c on p.company_id = c.id where company_id != 5;

select c.name company, count(*) count
from person as p join company as c
on p.company_id = c.id
group by c.name
having count(*) =
(
    select count(*) kk
    from person as p join company as c
    on p.company_id = c.id
    group by c.name
    order by kk desc
    limit 1
);
