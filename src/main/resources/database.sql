-- Создание таблицы со станциями
create table railway_station
(
    id      serial
        primary key,
    address varchar(255),
    name    varchar(255)
);

alter table railway_station
    owner to postgres;

-- Создание таблицы с путями
create table railway
(
    id                 serial
        primary key,
    railway_station_id integer
        constraint fko8ped5ontb4mr9tl71j1b9qv2
            references railway_station
);

alter table railway
    owner to postgres;

-- Создание таблицы с паспортами вагонов
create table train_car
(
    id            serial
        primary key,
    load_capacity integer not null,
    tare_weight   integer not null,
    type          varchar(255)
);

alter table train_car
    owner to postgres;

--Создание таблицы с грузами
create table cargo
(
    id   serial
        primary key,
    name varchar(255)
);

alter table cargo
    owner to postgres;



-- Создание таблицы с загруженными вагонами
create table loaded_train_car
(
    id               serial
        primary key,
    cargo_weight     integer not null,
    serial_number    integer not null,
    train_car_weight integer not null,
    cargo_id         integer
        constraint fkfnn440jdh9mdrk72q2iv0v3hc
            references cargo,
    railway_id       integer
        constraint fkcgtaffp3cxfnhyye9ksmd0xvw
            references railway,
    train_car_id     integer
        constraint fkmbpqyjqifcp4m1s4rinhndpcs
            references train_car
);

alter table loaded_train_car
    owner to postgres;

-- Создание sequence


-- Создание таблицы с пользователями

CREATE SEQUENCE user_table_seq START 2 INCREMENT 1;

create table user_table
(
    id       bigint
        primary key default nextval('user_table_seq'),
    password varchar(255),
    username varchar(255)
        constraint uken3wad7p8qfu8pcmh62gvef6v
            unique
);

alter table user_table
    owner to postgres;

alter sequence user_table_seq owner to postgres;

alter sequence user_table_seq owned by user_table.id;


CREATE SEQUENCE roles_seq START 3 INCREMENT 1;

-- Создание таблицы ролей
create table roles
(
    id   bigint
        primary key default (nextval('roles_seq')),
    name varchar(60)
);

alter table roles
    owner to postgres;

alter sequence roles_seq owner to postgres;

alter sequence roles_seq owned by roles.id;


-- Отношение многие ко многим (пользовали - роли)

create table user_roles
(
    user_id integer not null
        constraint fkoxgn0x8mxxu1hovew528qckp9
            references user_table,
    role_id bigint  not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles,
    primary key (user_id, role_id)
);

alter table user_roles
    owner to postgres;

-- Создание ролей
INSERT INTO roles VALUES(1, 'ADMIN');
INSERT INTO roles VALUES(2, 'USER');


-- Создание пользователя с ролью ADMIN

INSERT INTO user_table VALUES(1, '$2a$12$yV1lzYNebx/gFdzsei/Mm.H05auolGQp2bxBDyEMrqSBlrNPr1vHy','administrator');
INSERT INTO user_roles VALUES (1,1);







