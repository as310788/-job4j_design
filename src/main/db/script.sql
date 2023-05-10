create table hospitals(
id serial primary key,
doctor varchar(50),
name text,
price money
);
select * from hospitals;
insert into hospitals(id, doctor, name, price) values('1', 'Зубной врач', 'Зильберма', '100');
select * from hospitals;
update hospitals set doctor= 'Проктолог';
select * from hospitals;
delete from hospitals;
select * from hospitals;