DROP DATABASE IF EXISTS filmtvtracker;
CREATE DATABASE IF NOT EXISTS filmtvtracker;
USE filmtvtracker;

CREATE TABLE IF NOT EXISTS film(
	film_id BIGINT NOT NULL AUTO_INCREMENT,
    film_name VARCHAR(50) NOT NULL,
    director VARCHAR(50) NOT NULL,
    year_release SMALLINT NOT NULL,
    genre VARCHAR(20) NOT NULL,
    runtime INT NOT NULL,
    PRIMARY KEY (film_id)
);

CREATE TABLE IF NOT EXISTS tvshow(
	show_id BIGINT NOT NULL AUTO_INCREMENT,
    show_name VARCHAR(50) NOT NULL,
    year_began SMALLINT NOT NULL,
    year_ended SMALLINT,
    genre VARCHAR(20) NOT NULL,
    episodes INT NOT NULL,
    seasons INT NOT NULL,
    PRIMARY KEY (show_id)
);

CREATE TABLE IF NOT EXISTS watch_list(
	id BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    film_id BIGINT,
    show_id BIGINT,
    `status` VARCHAR(15) NOT NULL,
    total INT NOT NULL,
    progress INT NOT NULL,
    genre VARCHAR(20) NOT NULL,
    rating INT,
    PRIMARY KEY (id),
    FOREIGN KEY (film_id) REFERENCES film(film_id),
    FOREIGN KEY (show_id) REFERENCES tvshow(show_id)
);

INSERT INTO film (film_name, director, year_release, genre, runtime) VALUES ('The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson', 2001, 'Fantasy', 208);
INSERT INTO film (film_name, director, year_release, genre, runtime) VALUES ('The Godfather', 'Francis Ford Coppola', 1972, 'Mafia', 175);
INSERT INTO tvshow (show_name, year_began, year_ended, genre, episodes, seasons) VALUES ('Breaking Bad', 2008, 2013, 'Drama', 62, 5);
INSERT INTO tvshow (show_name, year_began, year_ended, genre, episodes, seasons) VALUES ('Gintama', 2006, 2021, 'Comedy/Action', 367, 4);

INSERT INTO watch_list (`name`, film_id, show_id, `status`, total, progress, genre, rating) VALUES ('The Lord of the Rings: The Fellowship of the Ring', 1, NULL, 'Complete', 1, 1, 'Fantasy', 10);
INSERT INTO watch_list (`name`, film_id, show_id, `status`, total, progress, genre, rating) VALUES ('Gintama', NULL, 2, 'Watching', 367, 100, 'Comedy/Action', 10);
