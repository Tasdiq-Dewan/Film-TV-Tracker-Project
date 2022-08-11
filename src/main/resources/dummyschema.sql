DROP TABLE IF EXISTS film;
DROP TABLE IF EXISTS tvshow;
DROP TABLE IF EXISTS watchlist;

CREATE TABLE IF NOT EXISTS film(
	film_id INT NOT NULL AUTO_INCREMENT,
    film_name VARCHAR(50) NOT NULL,
    director VARCHAR(50) NOT NULL,
    year_released SMALLINT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    runtime INT NOT NULL,
    PRIMARY KEY (film_id)
);
CREATE TABLE IF NOT EXISTS tvshow(
	show_id INT NOT NULL AUTO_INCREMENT,
    show_name VARCHAR(50) NOT NULL,
    year_began SMALLINT NOT NULL,
    year_ended SMALLINT,
    genre VARCHAR(50) NOT NULL,
    episodes INT NOT NULL,
    seasons INT NOT NULL,
    PRIMARY KEY (show_id)
);
CREATE TABLE IF NOT EXISTS watchlist(
	id INT NOT NULL AUTO_INCREMENT,
    film_id INT NOT NULL,
    show_id INT NOT NULL,
    `status` VARCHAR(50) NOT NULL,
    total INT NOT NULL,
    progress INT NOT NULL,
    rating INT,
    PRIMARY KEY (id),
    FOREIGN KEY (show_id) REFERENCES tvshow(show_id),
    FOREIGN KEY (film_id) REFERENCES film(film_id)
);

