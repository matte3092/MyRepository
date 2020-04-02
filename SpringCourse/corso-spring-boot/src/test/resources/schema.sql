CREATE TABLE public.table_user (
	id_user numeric NOT NULL AUTO_INCREMENT,
	name varchar NOT NULL,
	age numeric NOT NULL,
	PRIMARY KEY (id_user)
);


CREATE SEQUENCE public.user_table_id_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 100000
	START WITH 5
	CACHE 100
	NO CYCLE;