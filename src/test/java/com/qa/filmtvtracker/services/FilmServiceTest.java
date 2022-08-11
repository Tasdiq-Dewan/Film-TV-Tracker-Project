package com.qa.filmtvtracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.filmtvtracker.config.AppConfig;
import com.qa.filmtvtracker.entities.Film;
import com.qa.filmtvtracker.entities.FilmDTO;
import com.qa.filmtvtracker.repo.FilmRepo;

@SpringBootTest(classes= {FilmService.class, FilmRepo.class, AppConfig.class})
@ActiveProfiles("test")
public class FilmServiceTest {
	@Autowired
	FilmService service;
	
	@MockBean
	FilmRepo repo;
	
	@Autowired
	ModelMapper mapper;
	
	@Test
	public void testCreate() {
		Film film = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		
		Mockito.when(repo.save(film)).thenReturn(film);
		assertEquals(service.addFilm(film), film);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(film);
		
	}
	
	@Test
	public void testGetAll() {
		Film film = new Film(1L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
		List<Film> list = new ArrayList<Film>();
		list.add(film);
		FilmDTO filmdto = mapper.map(list, FilmDTO.class);
		List<FilmDTO> listdto = new ArrayList<FilmDTO>();
		listdto.add(filmdto);
		List<FilmDTO> output = service.getAllFilms();
		
		Mockito.when(repo.findAll()).thenReturn(list);
		assertEquals(output, listdto);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
}
