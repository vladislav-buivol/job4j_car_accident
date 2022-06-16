CREATE TABLE accident
(
    id               serial primary key,
    name             varchar(2000),
    text             varchar(2000),
    address          varchar(2000),
    accident_type_id int REFERENCES accident_type (id)
);

CREATE TABLE accident_type
(
    id   serial primary key,
    name varchar(2000)
);

CREATE TABLE rule
(
    id   serial primary key,
    name varchar(2000)
);

CREATE TABLE accident_rule
(
    id          serial primary key,
    accident_id int REFERENCES accident (id),
    rule_id     int REFERENCES rule (id)
);

insert into accident_type(name)
values ('Две машины'),
       ('Машина и человек'),
       ('Машина и велосипед');

insert into rule(name)
values ('Статья. 1'),
       ('Статья. 2'),
       ('Статья. 3');


insert into accident(name, text, address, type_id)
values ('Accident 1', 'Desc', 'Address', 1),
       ('Accident 2', 'Desc', 'Address', 2),
       ('Accident 3', 'Desc', 'Address', 3);

CREATE TABLE authorities (
                             id serial primary key,
                             authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
                       id serial primary key,
                       username VARCHAR(50) NOT NULL unique,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
        (select id from authorities where authority = 'ROLE_ADMIN'));