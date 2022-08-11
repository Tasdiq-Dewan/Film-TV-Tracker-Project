package com.qa.filmtvtracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		List<FilmDTO> output = service.getAllFilms();
		
		Mockito.when(repo.findAll()).thenReturn(list);
		assertEquals(output, expected);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	public void delete() {
		Long id = 1L;
		Mockito.when(repo.existsById(id)).thenReturn(true, false);
		boolean expected = true;
		boolean response = service.removeFilm(id);
		assertEquals(expected, response);
		Mockito.verify(this.repo, Mockito.times(2)).existsById(id);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
	
	@Test
	public void deleteIllID() {
		Long id = 1L;
		
		Mockito.when(repo.existsById(id)).thenReturn(false);
		Exception thrown = Assertions.assertThrows(FilmNotFoundException.class, ()->{service.removeFilm(id);});
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
}
