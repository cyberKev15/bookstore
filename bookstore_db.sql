drop database bookstoredb;
drop user bookstore;
create user bookstore with password "group12";
create database bookstoredb with template=template0 owner:bookstore;
\connect bookstoredb;
alter default privileges grant all on tables to bookstore;
alter default privileges grant all on sequences to bookstore;

create table users(
user_email varchar(30) primary key not null,
password text not null,
user_name varchar (20),
user_address varchar (100));

create sequence users increment 1 start 1;



