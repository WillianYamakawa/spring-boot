-- tabela usuario
create table usuario (
     id int auto_increment primary key,
     usuario varchar(100) not null,
     senha_hash varchar(255) not null,
     ativo tinyint(1) not null default 1,
     data_criacao datetime default current_timestamp
);

insert into usuario (usuario, senha_hash)
values ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');

-- tabela cliente
create table cliente (
     id int auto_increment primary key,
     nome varchar(150) not null,
     documento varchar(20) unique,
     telefone varchar(20),
     email varchar(120),
     endereco varchar(200),
     ativo tinyint(1) not null default 1,
     data_cadastro datetime default current_timestamp
);

-- tabela produto
create table produto (
     id int auto_increment primary key,
     nome varchar(150) not null,
     descricao varchar(255),
     preco decimal(10,2) not null,
     estoque int not null default 0,
     ativo tinyint(1) not null default 1,
    data_cadastro datetime default current_timestamp
);

-- tabela venda
cliente_id int not null,
    usuario_id int not null,
    data_venda datetime default current_timestamp,
    ativo tinyint(1) not null default 1,
    valor_total decimal(10,2) not null default 0,
create table venda (
    id int auto_increment primary key,

    foreign key (cliente_id) references cliente(id),
    foreign key (usuario_id) references usuario(id)
);

-- tabela venda_item
create table venda_item (
    id int auto_increment primary key,
    venda_id int not null,
    produto_id int not null,
    quantidade int not null default 1,
    preco_unitario decimal(10,2) not null,
    total decimal(10,2) not null,

    foreign key (venda_id) references venda(id),
    foreign key (produto_id) references produto(id)
);
