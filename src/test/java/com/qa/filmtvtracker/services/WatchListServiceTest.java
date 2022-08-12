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
import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.entities.WatchList;
import com.qa.filmtvtracker.entities.WatchListDTO;
import com.qa.filmtvtracker.exceptions.FilmNotFoundException;
import com.qa.filmtvtracker.repo.WatchListRepo;

@SpringBootTest(classes = {WatchListService.class, WatchListRepo.class, AppConfig.class})
@ActiveProfiles("test")
public class WatchListServiceTest {
	@Autowired
	WatchListService service;
	
	@MockBean
	WatchListRepo repo;
	
	private final Long FILMID = 1L;
	private final Film FILM = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
	private final Long SHOWID = 2L;
	private final TVShow SHOW = new TVShow(2L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
	private final WatchList ENTRY1 = new WatchList(1L, FILM, null, "The Lord of the Rings: The Fellowship of the Ring", "Complete", 1 , 1, "Fantasy", 10);
	private final WatchList ENTRY2 = new WatchList(2L, null, SHOW, "Gintama", "Watching", 367 , 100, "Comedy/Action", 10);
	
	@Test
	public void testGetAll() {
		List<WatchList> list = List.of(ENTRY1);
		WatchListDTO entrydto = new WatchListDTO(1L, FILM, null, "The Lord of the Rings: The Fellowship of the Ring", "Complete", 1 , 1, "Fantasy", 10);
		List<WatchListDTO> expected = List.of(entrydto);
		Mockito.when(this.repo.findAll()).thenReturn(list);
		assertEquals(expected, service.getAllWatchListEntries());
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testAddFilm() {
		Film film = FILM;
		WatchList create = ENTRY1;
		create.setId(null);
		String status = create.getStatus();
		int progress = create.getProgress();
		create.setRating(0);
		Mockito.when(this.repo.save(create)).thenReturn(create);
		//WatchList response = service.addFilmEntry(film, status, progress);
		assertEquals(create, service.addFilmEntry(film, status, progress));
		Mockito.verify(this.repo, Mockito.times(1)).save(create);
	}
	
	@Test
	public void testAddShow() {
		TVShow show = SHOW;
		WatchList create = ENTRY2;
		create.setId(null);
		String status = create.getStatus();
		int progress = create.getProgress();
		create.setRating(0);
		Mockito.when(this.repo.save(create)).thenReturn(create);
		assertEquals(create, service.addShowEntry(show, status, progress));
		Mockito.verify(this.repo, Mockito.times(1)).save(create);
	}
}
