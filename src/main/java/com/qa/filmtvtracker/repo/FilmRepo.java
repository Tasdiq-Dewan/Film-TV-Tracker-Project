package com.qa.filmtvtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.filmtvtracker.entities.Film;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long> {
	List<Film> findFilmByFilmName(String name);
	List<Film> findFilmByGenre(String genre);
	List<Film> findFilmByDirector(String director);
	List<Film> findFilmByYearRelease(short year);
}
