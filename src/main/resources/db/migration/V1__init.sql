
create table fornecedor
(
  id bigint auto_increment
    primary key,
  nome varchar(50) not null,
  cnpj varchar(14)    null
);

create table produto
(
  id bigint auto_increment
    primary key,
  cod_produto varchar(30),  descricao     varchar(50) not null,
  fk_fornecedor bigint         null,
  constraint produto_fornecedor_id_fk
    foreign key (fk_fornecedor) references fornecedor (id)
);

create table fornecedor_produto
(
  fornecedor_id bigint not null,
  produto_id    bigint not null,
  constraint fornecedor_fk
    foreign key (fornecedor_id) references fornecedor (id),
  constraint produto_fk
    foreign key (produto_id) references produto (id)
);