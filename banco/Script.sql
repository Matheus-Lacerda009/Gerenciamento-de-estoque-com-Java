create database EmpresaX;
use EmpresaX;

create table Estoque(
	id int auto_increment primary key,
    nome varchar(100) not null,
    preco decimal(10, 2) not null,
    quantidade int not null,
    ativo bool default true
);

select * from Estoque;