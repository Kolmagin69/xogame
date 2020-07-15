CREATE USER xogame WITH PASSWORD '123321';
CREATE DATABASE xogame;
GRANT ALL PRIVILEGES  DATABASE xogame TO xogame;

CREATE TABLE fields(
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	size int,
	figures varchar(1)[][]
);

CREATE TABLE games (
	id uuid PRIMARY KEY,
	type varchar(255),
	name varchar(255),
	turn varchar(1),
	winner varchar(1),
	field_id int REFERENCES fields(id)
);

CREATE TABLE players (
	id uuid PRIMARY KEY,
	name varchar(255),
	figure varchar(1),
	game_id uuid REFERENCES GAMES(id)
);
