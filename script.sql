create database if not exists dbproducts; 

use dbproducts;

create table if not exists Produit (
	id int primary key auto_increment, 
	nom varchar(30),
	famille varchar(30),
	prix_achat double,
	prix_vente double  
); 

insert into Produit values (null,'Produit 1', "Textile",33,40);
insert into Produit values (null,'Produit 2', "Epicerie",20,33);
insert into Produit values (null,'Produit 3', "Epicerie",10,13);
insert into Produit values (null,'Produit 4', "Textile",2,5);