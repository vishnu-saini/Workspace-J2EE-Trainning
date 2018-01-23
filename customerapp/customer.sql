

create database customerdb;

use customerdb;

drop table IF exists Customer ;


create table customer (
	id integer not null auto_increment, 
	name varchar(255), 
	address varchar(255), 
	phone varchar(255), 
	primary key (id)
);


create table user (
	id integer not null auto_increment, 
	username varchar(255), 
	password varchar(255),
	primary key (id)
);

insert into user (username,password)
values ("root","root");


