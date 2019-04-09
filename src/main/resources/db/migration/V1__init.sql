create table providers
(
    id    bigint auto_increment
        primary key,
    name  varchar(50) not null,
    cnpj  varchar(14) not null,
    email varchar(30) null

);

create table adresses
(
    id           bigint auto_increment
        primary key,
    cep          varchar(8),
    street       varchar(50),
    number       varchar(6),
    neighborhood varchar(30),
    city         varchar(30),
    estate       varchar(2),
    provider_id  bigint not null,
    constraint address_provider_id_fk foreign key (provider_id) references providers (id)
);

create table products
(
    id          bigint auto_increment
        primary key,
    cod_product varchar(30),
    description varchar(50) not null,
    price       float,
    provider_id bigint      not null,
    constraint product_provider_id_fk foreign key (provider_id) references providers (id)
);