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

import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.main.FilmTvTrackerProjectApplication;

@SpringBootTest(classes = {FilmTvTrackerProjectApplication.class})
@ActiveProfiles("test")
public class TVShowRepoTest {
	@Autowired
	TVShowRepo repo;
	
	@BeforeEach
	public void dbWipe() {
		
	}
	
	@Test
	public void testFindId() {
		TVShow show = new TVShow(1L, "Breaking Bad", (short) 2008, (short) 2013, "Drama", 62, 5);
		Long id = 1L;
		Optional<TVShow> expected = Optional.of(show);
		assertEquals(expected, repo.findById(id));
	}
	
	@Test
	public void testFindAll() {
		TVShow show1 = new TVShow(1L, "Breaking Bad", (short) 2008, (short) 2013, "Drama", 62, 5);
		TVShow show2 = new TVShow(2L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShow> expected = List.of(show1, show2);
		assertEquals(expected, repo.findAll());
	}
}
