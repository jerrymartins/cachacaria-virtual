
create table fornecedores
(
  id bigint auto_increment
    primary key,
  nome varchar(50) not null,
  cnpj varchar(14)    null,
  email varchar(30) null
);

create table produtos
(
  id bigint auto_increment
    primary key,
  cod_produto varchar(30),
  descricao varchar(50) not null,
  fornecedor_id bigint not null,
  constraint produto_fornecedor_id_fk
    foreign key (fornecedor_id) references fornecedores (id)
);