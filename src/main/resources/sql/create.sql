CREATE USER xogame WITH PASSWORD '123321';
CREATE DATABASE xogame;
GRANT ALL PRIVILEGES ON DATABASE xogame TO xogame;


CREATE TABLE fields(
	id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
	size int NOT NULL,
	figures varchar(1)[][]
);

CREATE TABLE players (
	id uuid PRIMARY KEY,
	name varchar(255),
);

CREATE TABLE player_figures (
    id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    player uuid REFERENCES players(id),
    figure id REFERENCES figures(id)
);

CREATE TABLE games (
	id uuid PRIMARY KEY,
	type varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	turn varchar(1) NOT NULL,
	winner varchar(1),
	field_id int,
	player_1 int,
	player_2 int,
	FOREIGN KEY (field_id) REFERENCES fields(id) ON DELETE CASCADE,
	FOREIGN KEY (player_1) REFERENCES player_figures(id) ON DELETE CASCADE,
	FOREIGN KEY (player_2) REFERENCES player_figures(id) ON DELETE CASCADE
);

