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


insert into accident(name, text, address)
values ('Accident 1', 'Desc', 'Address'),
       ('Accident 2', 'Desc', 'Address'),
       ('Accident 3', 'Desc', 'Address');