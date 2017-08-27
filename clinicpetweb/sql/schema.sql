-- create table
create table pet (
	uid serial primary key,
	name varchar(200),
	kind varchar(200),
	age int
);

create table client (
	uid serial primary key,
	name varchar(200),
	pet_id int not null references pet(uid)
);

-- add new pet
insert into pet (name, kind, age) values ('sparky', 'Dog', 8);

insert into pet (name, kind, age) values ('boby', 'Cat', 10);

-- add new client
insert into client (name, pet_id) values ('petr', 1);

insert into client (name, pet_id) values ('vasya', 2);


-- select pet with client
select client.name, pet.name from pet as pet, client as client where client.pet_id = pet.uid;

-- select client by name
select * from client where client.name = 'petr'

-- update pet
update pet set name = 'bob' where pet.name = 'boby'

-- delete pet by name
delete from pet as pet where pet.name = 'bob'

-- roles
create table roles (
		uid serial primary key,
		name varchar(200)
);

-- users
create table users (
		uid serial primary key,
		login varchar(200),
		email varchar(200),
		role_id int not null references roles(uid)
);

-- messages

-- users
create table messages (
		uid serial primary key,
		text  character varying,
		user_id int not null references users(uid)
);