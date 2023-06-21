CREATE SCHEMA learnUp

CREATE TABLE "user"
(
id serial not null unique primary key,
"name" text not null ,
surname text not null ,
birth_date date,
address text
);

INSERT INTO "user" ("name", surname, birth_date, address) values ('Petya', 'Koslov', '2001-05-01', 'Russia');

SELECT * FROM public.user WHERE id = 2;
SELECT * FROM "user" WHERE id = 3;

delete from "user" where "id" = 5;

select * from "user"