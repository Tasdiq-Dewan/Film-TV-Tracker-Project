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
import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.entities.WatchList;
import com.qa.filmtvtracker.main.FilmTvTrackerProjectApplication;

@SpringBootTest(classes = {FilmTvTrackerProjectApplication.class})
@ActiveProfiles("test")
public class WatchListRepoTest {
	
	@Autowired
	WatchListRepo repo;
	
	private final Long FILMID = 1L;
	private final Film FILM = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
	private final Long SHOWID = 2L;
	private final TVShow SHOW = new TVShow(2L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
	private final WatchList ENTRY1 = new WatchList(1L, FILM, null, "The Lord of the Rings: The Fellowship of the Ring", "Complete", 1 , 1, "Fantasy", 10);
	private final WatchList ENTRY2 = new WatchList(2L, null, SHOW, "Gintama", "Watching", 367 , 100, "Comedy/Action", 10);
	
	@BeforeEach
	public void dbWipe() {
		
	}
	
	@Test
	public void testFindById() {
		Long id = 1L;
		
		Optional<WatchList> expected = Optional.of(ENTRY1);
		assertEquals(expected, this.repo.findById(id));
	}
	
	@Test
	public void testFindAll() {
		List<WatchList> expected = List.of(ENTRY1, ENTRY2);
		assertEquals(expected, this.repo.findAll());
	}
	
	@Test
	public void testSave() {
		WatchList expected = ENTRY1;
		assertEquals(expected, this.repo.save(ENTRY1));
	}

}
