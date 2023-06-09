insert into role(name) values ('Роль 1'), ('Роль 2');
insert into state(state) values ('Готова'), ('В обработке');
insert into category(category) values ('Общая'), ('Специальная');
insert into rules(user_right) values ('Главная'), ('Второстепенная');
insert into users(name, role_id) values ('Вася', 1), ('Евгений', 1), ('Анна', 2);
insert into role_rules(role_id, rules_id) values (1,1), (2,2);
insert into item(item, user_id, category_id, state_id)
values ('item 1', 1, 1, 2), ('item 2', 2, 2, 1), ('item 3', 3, 2, 1);
insert into comments(comment, item_id) values ('Комментарий 1', 1), ('Коменнтарий 2', 3);
insert into attachs(file, item_id) values ('Файл 1', 2), ('Файл 2', 3);