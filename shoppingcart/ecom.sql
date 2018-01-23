show databases;
use ecom;


show tables;

create table product (
	id integer not null auto_increment, 
	name varchar(255), 
	price varchar(255),
    categry varchar(255),
	primary key (id)
);