package com.qa.filmtvtracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.filmtvtracker.config.AppConfig;
import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.entities.TVShowDTO;
import com.qa.filmtvtracker.repo.TVShowRepo;

@SpringBootTest(classes= {TVShowService.class, TVShowRepo.class, AppConfig.class})
@ActiveProfiles("test")
public class TVShowServiceTest {
	@Autowired
	TVShowService service;
	
	@MockBean
	TVShowRepo repo;
	
	@Test
	public void testCreate() {
		TVShow show = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		Mockito.when(repo.save(show)).thenReturn(show);
		assertEquals(show, service.addShow(show));
		Mockito.verify(this.repo, Mockito.times(1)).save(show);
	}
	
	@Test
	public void testGetShow() {
		TVShow show = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		Optional<TVShow> op = Optional.of(show);
		TVShowDTO expected = new TVShowDTO(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		Long id = 1L;

		Mockito.when(repo.findById(id)).thenReturn(op);
		TVShowDTO response = service.getShow(id);
		assertEquals(expected, response);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	public void testGetAll() {
		TVShow show = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShow> list = List.of(show);
		TVShowDTO showdto = new TVShowDTO(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShowDTO> expected = List.of(showdto);
		Mockito.when(repo.findAll()).thenReturn(list);
		assertEquals(expected, service.getAllShows());
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void testSearchName() {
		String name = "Gintama";
		TVShow show = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShow> list = List.of(show);
		TVShowDTO showdto = new TVShowDTO(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShowDTO> expected = List.of(showdto);
		Mockito.when(repo.findTVShowByShowName(name)).thenReturn(list);
		assertEquals(expected, service.searchShowsByName(name));
		Mockito.verify(this.repo, Mockito.times(1)).findTVShowByShowName(name);
	}
	
	@Test
	public void testSearchGenre() {
		String genre= "Comedy/Action";
		TVShow show = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShow> list = List.of(show);
		TVShowDTO showdto = new TVShowDTO(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShowDTO> expected = List.of(showdto);
		Mockito.when(repo.findTVShowByGenre(genre)).thenReturn(list);
		assertEquals(expected, service.searchShowsByGenre(genre));
		Mockito.verify(this.repo, Mockito.times(1)).findTVShowByGenre(genre);
	}
	
	@Test
	public void testSearchYear() {
		short year = 2006;
		TVShow show = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShow> list = List.of(show);
		TVShowDTO showdto = new TVShowDTO(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShowDTO> expected = List.of(showdto);
		Mockito.when(repo.findTVShowByYearBegan(year)).thenReturn(list);
		assertEquals(expected, service.searchShowsByYearBegan(year));
		Mockito.verify(this.repo, Mockito.times(1)).findTVShowByYearBegan(year);
	}
	
	@Test
	public void testSearchEpisodes() {
		int episodes = 367;
		TVShow show = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShow> list = List.of(show);
		TVShowDTO showdto = new TVShowDTO(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShowDTO> expected = List.of(showdto);
		Mockito.when(repo.findTVShowByEpisodes(episodes)).thenReturn(list);
		assertEquals(expected, service.searchShowsByEpisodes(episodes));
		Mockito.verify(this.repo, Mockito.times(1)).findTVShowByEpisodes(episodes);
	}
	
	@Test
	public void testSearchSeasons() {
		int seasons = 4;
		TVShow show = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShow> list = List.of(show);
		TVShowDTO showdto = new TVShowDTO(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		List<TVShowDTO> expected = List.of(showdto);
		Mockito.when(repo.findTVShowBySeasons(seasons)).thenReturn(list);
		assertEquals(expected, service.searchShowsBySeasons(seasons));
		Mockito.verify(this.repo, Mockito.times(1)).findTVShowBySeasons(seasons);
	}
	
	@Test
	public void testUpdate() {
		TVShow show = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		Optional<TVShow> op = Optional.of(show);
		Long id = 1L;
		TVShow updated = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action/Sci-Fi", 367, 4);
		Mockito.when(repo.findById(id)).thenReturn(op);
		Mockito.when(repo.save(updated)).thenReturn(updated);
		TVShow response = service.updateShow(id, updated);
		assertEquals(updated, response);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
	}
	
	@Test 
	public void testDelete() {
		Long id = 1L;
		Mockito.when(repo.existsById(id)).thenReturn(true, false);
		assertTrue(service.removeShow(id));
		Mockito.verify(this.repo, Mockito.times(2)).existsById(id);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
	}
}
