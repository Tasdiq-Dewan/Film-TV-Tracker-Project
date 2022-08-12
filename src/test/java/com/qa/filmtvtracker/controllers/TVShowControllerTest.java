package com.qa.filmtvtracker.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.filmtvtracker.entities.Film;
import com.qa.filmtvtracker.entities.FilmDTO;
import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.entities.TVShowDTO;
import com.qa.filmtvtracker.main.FilmTvTrackerProjectApplication;

@SpringBootTest(classes= {FilmTvTrackerProjectApplication.class, TVShowController.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TVShowControllerTest {

	@Autowired
	MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectMapper jsonifier;
	
	private TVShowDTO mapToDTO(TVShow show) {
		return mapper.map(show, TVShowDTO.class);
	}
	
	private final Long TEST_ID1 = 1L;
	private final TVShow TEST_SHOW1 = new TVShow(1L, "Breaking Bad", (short) 2008, (short) 2013, "Drama", 62, 5);
	
	private final Long TEST_ID2 = 2L;
	private final TVShow TEST_SHOW2 = new TVShow(2L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
	
	private final Long TEST_ID3 = 3L;
	private final TVShow TEST_SHOW3 = new TVShow(null, "Cowboy Bebop", (short) 1998, (short) 1998, "Sci-Fi", 26, 1);
	
	@Test
	public void testCreate() {
		TVShow expected = TEST_SHOW3;
		expected.setShowId(TEST_ID3);
		try {

			mock.perform(post("/api/tvshows/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.content(this.jsonifier.writeValueAsString(TEST_SHOW3)))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDTO(expected))));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAll() {
		List<TVShowDTO> expected = List.of(mapToDTO(TEST_SHOW1), mapToDTO(TEST_SHOW2));
		try {

			mock.perform(get("/api/tvshows/getAll").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGet() {
		TVShowDTO expected = mapToDTO(TEST_SHOW1);
		try {

			mock.perform(get("/api/tvshows/getShow/"+TEST_ID1).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchName() {
		String name = TEST_SHOW1.getShowName();
		List<TVShowDTO> expected = List.of(mapToDTO(TEST_SHOW1));
		try {

			mock.perform(get("/api/tvshows/search/"+name).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchEpisodes() {
		int episodes = TEST_SHOW1.getEpisodes();
		List<TVShowDTO> expected = List.of(mapToDTO(TEST_SHOW1));
		try {

			mock.perform(get("/api/tvshows/searchBy?episodes="+episodes+"&seasons=0&genre=&year=0").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchSeasons() {
		int seasons = TEST_SHOW1.getSeasons();
		List<TVShowDTO> expected = List.of(mapToDTO(TEST_SHOW1));
		try {

			mock.perform(get("/api/tvshows/searchBy?episodes=0&seasons="+seasons+"&genre=&year=0").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchGenre() {
		String genre = TEST_SHOW1.getGenre();
		List<TVShowDTO> expected = List.of(mapToDTO(TEST_SHOW1));
		try {

			mock.perform(get("/api/tvshows/searchBy?episodes=0&seasons=0&genre="+genre+"&year=0").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByYear() {
		short year = TEST_SHOW1.getYearBegan();
		List<TVShowDTO> expected = List.of(mapToDTO(TEST_SHOW1));
		try {

			mock.perform(get("/api/tvshows/searchBy?episodes=0&seasons=0&genre=&year="+year).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByEmpty() {
		List<TVShowDTO> expected = List.of();
		try {

			mock.perform(get("/api/tvshows/searchBy?episodes=0&seasons=0&genre=&year=0").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		TVShow updated = TEST_SHOW1;
		Long id = TEST_ID1;
		try {

			mock.perform(put("/api/tvshows/updateShow/"+id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.content(this.jsonifier.writeValueAsString(updated)))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(updated)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		Long id = TEST_ID1;
		boolean expected = true;
		try {
			mock.perform(delete("/api/tvshows/delete/"+id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
