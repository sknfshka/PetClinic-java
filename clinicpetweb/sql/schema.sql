-- create table
create table pet (
	uid serial primary key,
	name varchar(200),
	kind varchar(200),
	age int,
	client_id int not null references client(uid)
);

create table client (
	uid serial primary key,
	name varchar(200)
);

-- add new pet
insert into pet (name, kind, age, client_id) values ('sparky', 'Dog', 8, 1);

insert into pet (name, kind, age, client_id) values ('boby', 'Cat', 10, 2);

-- add new client
insert into client (name) values ('petr');

insert into client (name) values ('vasya');

-- select pet with client
select client.name, pet.name from pet as pet, client as client where client.uid = pet.client_id;

-- select client by name
select * from client where client.name = 'petr'

-- update pet
update pet set name = 'bob' where pet.name = 'boby'

-- delete pet by name
delete from pet as pet where pet.name = 'bob'
