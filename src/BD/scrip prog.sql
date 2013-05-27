DROP DATABASE IF EXISTS tiendagafas;
CREATE DATABASE tiendagafas;
USE tiendagafas;
CREATE TABLE tipo (
    id_tipo  int unsigned auto_increment,
    solarGraduada VARCHAR(20) NOT NULL,
		constraint pk_tipo primary key (id_tipo)
);
CREATE TABLE gafas (
    id_gafas  int unsigned auto_increment,
    modelo VARCHAR(20) NOT NULL,
	marca VARCHAR(20) NOT NULL,
	precio int unsigned,
	color VARCHAR(20) NOT NULL,
	genero VARCHAR(20) NOT NULL,
	material VARCHAR(20) NOT NULL,
	forma VARCHAR(20) NOT NULL,
	id_tipo int unsigned,
	imagen blob,
		constraint pk_gafas primary key (id_gafas),
		constraint fk_relaccion foreign key (id_tipo)
			references tipo (id_tipo)
);

INSERT INTO tipo (id_tipo,solarGraduada) VALUES 
(1,'solar'),
(2,'graduada');

INSERT INTO gafas (id_gafas,modelo,marca,precio,color,genero,material,forma,id_tipo) VALUES 
(null,'Aviador','Ray Ban',120,'plata','masculino','aluminio','pera',1),
(null,'ms-653','Carrera',100,'blanco','masculino','metal','cuadrado',2),
(null,'simple','Vogue',80,'verde','masculino','titanio','cuadrada',1);



