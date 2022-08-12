INSERT INTO film (film_name, director, year_release, genre, runtime) VALUES ('The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson', 2001, 'Fantasy', 208);
INSERT INTO film (film_name, director, year_release, genre, runtime) VALUES ('The Godfather', 'Francis Ford Coppola', 1972, 'Mafia', 175);
INSERT INTO tvshow (show_name, year_began, year_ended, genre, episodes, seasons) VALUES ('Breaking Bad', 2008, 2013, 'Drama', 62, 5);
INSERT INTO tvshow (show_name, year_began, year_ended, genre, episodes, seasons) VALUES ('Gintama', 2006, 2021, 'Comedy/Action', 367, 4);

INSERT INTO watch_list (name, film_id, show_id, status, total, progress, genre, rating) VALUES ('The Lord of the Rings: The Fellowship of the Ring', 1, NULL, 'Complete', 1, 1, 'Fantasy', 10);
INSERT INTO watch_list (name, film_id, show_id, status, total, progress, genre, rating) VALUES ('Gintama', NULL, 2, 'Watching', 367, 100, 'Comedy/Action', 10);