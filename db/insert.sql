insert into role(name) values ('user');
insert into role(name) values ('admin');

insert into users(name, role_id) values ('Vasya', 1);
insert into users(name, role_id) values ('Petya', 2);

insert into rules(name) values ('delete');
insert into rules(name) values ('edit');

insert into role_rules(role_id, rule_id) values (1, 1);
insert into role_rules(role_id, rule_id) values (2, 1);
insert into role_rules(role_id, rule_id) values (2, 1);

insert into category(name) values ('urgent');
insert into category(name) values ('regular');

insert into state(name) values ('done');
insert into state(name) values ('in progress');

insert into item(name, user_id, category_id, state_id) values ('to close the task #1', 1, 1, 2);
insert into item(name, user_id, category_id, state_id) values ('to close the task #2', 2, 2, 1);

insert into comments(name, item_id) values ('cool', 1);
insert into comments(name, item_id) values ('well done', 2);

insert into attachs(name, item_id) values ('file', 1);
insert into attachs(name, item_id) values ('jpg', 2);









