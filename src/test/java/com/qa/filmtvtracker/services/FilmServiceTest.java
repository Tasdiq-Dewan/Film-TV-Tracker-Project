package com.qa.filmtvtracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.filmtvtracker.config.AppConfig;
import com.qa.filmtvtracker.entities.Film;
import com.qa.filmtvtracker.entities.FilmDTO;
import com.qa.filmtvtracker.exceptions.FilmNotFoundException;
import com.qa.filmtvtracker.repo.FilmRepo;

@SpringBootTest(classes= {FilmService.class, FilmRepo.class, AppConfig.class})
@ActiveProfiles("test")
public class FilmServiceTest {
	@Autowired
	FilmService service;
	
	@MockBean
	FilmRepo repo;
	
	@Test
	public void testCreate() {
		Film film = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		
		Mockito.when(repo.save(film)).thenReturn(film);
		assertEquals(service.addFilm(film), film);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(film);
		
	}
	
	@Test
	public void testGetFilm() {
		Film film = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		Optional<Film> op = Optional.of(film);
		FilmDTO expected = new FilmDTO(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		Long id = 1L;
		Mockito.when(repo.findById(id)).thenReturn(op);
		FilmDTO response = service.getFilm(id);
		assertEquals(expected, response);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}

	@Test
	public void testGetAll() {
		Film film = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<Film> list = new ArrayList<Film>();
		list.add(film);
		FilmDTO filmdto = new FilmDTO(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<FilmDTO> expected = new ArrayList<FilmDTO>();
		expected.add(filmdto);
		//List<FilmDTO> output = service.getAllFilms();
		
		Mockito.when(repo.findAll()).thenReturn(list);
		assertEquals(expected, service.getAllFilms());
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	public void delete() {
		Long id = 1L;
		Mockito.when(repo.existsById(id)).thenReturn(true, false);
//		boolean expected = true;
//		boolean response = service.removeFilm(id);
		assertTrue(service.removeFilm(id));
		Mockito.verify(this.repo, Mockito.times(2)).existsById(id);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
	
	@Test
	public void testDeleteNotFound() {
		Long id = 1L;
		
		Mockito.when(repo.existsById(id)).thenReturn(false);
		Exception thrown = Assertions.assertThrows(FilmNotFoundException.class, ()->{service.removeFilm(id);});
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
	
	@Test
	public void testUpdate() {
		Film film = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		Film updated = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero/Action", 126);
		Optional<Film> op = Optional.of(film);
		Long id = 1L;
		Mockito.when(repo.findById(id)).thenReturn(op);
		Mockito.when(repo.save(updated)).thenReturn(updated);
		assertEquals(updated, service.updateFilm(id, updated));
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
	}
	
	@Test
	public void testSearchName() {
		String name = "Batman";
		Film film = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<Film> list = List.of(film);
		FilmDTO filmdto = new FilmDTO(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<FilmDTO> expected = List.of(filmdto);
		
		Mockito.when(repo.findFilmByFilmName(name)).thenReturn(list);
		assertEquals(expected, service.searchFilmsByName(name));
		Mockito.verify(this.repo, Mockito.times(1)).findFilmByFilmName(name);
		
	}
	
	@Test
	public void testSearchDirector() {
		String director = "Tim Burton";
		Film film = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<Film> list = List.of(film);
		FilmDTO filmdto = new FilmDTO(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<FilmDTO> expected = List.of(filmdto);
		
		Mockito.when(repo.findFilmByDirector(director)).thenReturn(list);
		assertEquals(expected, service.searchFilmsByDirector(director));
		Mockito.verify(this.repo, Mockito.times(1)).findFilmByDirector(director);
	}
	
	@Test
	public void testSearchYear() {
		short year = 1989;
		Film film = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<Film> list = List.of(film);
		FilmDTO filmdto = new FilmDTO(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<FilmDTO> expected = List.of(filmdto);
		
		Mockito.when(repo.findFilmByYearRelease(year)).thenReturn(list);
		assertEquals(expected, service.searchFilmsByYear(year));
		Mockito.verify(this.repo, Mockito.times(1)).findFilmByYearRelease(year);
	}
	
	@Test
	public void testSearchGenre() {
		String genre = "Superhero";
		Film film = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<Film> list = List.of(film);
		FilmDTO filmdto = new FilmDTO(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<FilmDTO> expected = List.of(filmdto);
		
		Mockito.when(repo.findFilmByGenre(genre)).thenReturn(list);
		assertEquals(expected, service.searchFilmsByGenre(genre));
		Mockito.verify(this.repo, Mockito.times(1)).findFilmByGenre(genre);
	}
}
