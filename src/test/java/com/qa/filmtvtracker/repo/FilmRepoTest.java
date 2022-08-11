package com.qa.filmtvtracker.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.qa.filmtvtracker.entities.Film;
import com.qa.filmtvtracker.main.FilmTvTrackerProjectApplication;

@SpringBootTest(classes = {FilmTvTrackerProjectApplication.class})
@ActiveProfiles("test")
public class FilmRepoTest {

	@Autowired
	FilmRepo repo;
	
	@BeforeEach
	public void dbWipe() {
		
	}
	
	@Test
	public void testFindId() {
		Long id = 1L;
		Film film = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
		Optional<Film> expected = Optional.of(film);
		assertEquals(expected, repo.findById(id));
	}
	
	@Test
	public void testFindAll() {
		Film film1 = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
		Film film2 = new Film(2L, "The Godfather", "Francis Ford Coppola", (short) 1972, "Mafia", 175);
		List<Film> expected = List.of(film1, film2);
		assertEquals(expected, repo.findAll());
	}
	
	@Test
	public void testSave() {
		Film film = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
		assertEquals(film, repo.save(film));
	}
	
	@Test
	public void testFindName() {
		String name = "The Lord of the Rings: The Fellowship of the Ring";
		Film film = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
		List<Film> expected = List.of(film);
		assertEquals(expected, repo.findFilmByFilmName(name));
	}
	
	@Test
	public void testFindDirector() {
		String director = "Peter Jackson";
		Film film = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
		List<Film> expected = List.of(film);
		assertEquals(expected, repo.findFilmByDirector(director));
	}
	
	@Test
	public void testFindYear() {
		short year = 2001;
		Film film = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
		List<Film> expected = List.of(film);
		assertEquals(expected, repo.findFilmByYearRelease(year));
	}
	
	@Test
	public void testFindGenre() {
		String genre = "Fantasy";
		Film film = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
		List<Film> expected = List.of(film);
		assertEquals(expected, repo.findFilmByGenre(genre));
	}
	
	@Test
	public void testExists() {
		Long id = 1L;
		assertTrue(repo.existsById(id));
	}
}
