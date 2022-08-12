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
import com.qa.filmtvtracker.entities.WatchList;
import com.qa.filmtvtracker.entities.WatchListDTO;
import com.qa.filmtvtracker.main.FilmTvTrackerProjectApplication;

@SpringBootTest(classes = {FilmTvTrackerProjectApplication.class, WatchListController.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class WatchListControllerTest {

	@Autowired
	MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectMapper jsonifier;
	
	private WatchListDTO mapToDTO(WatchList entry) {
		return mapper.map(entry, WatchListDTO.class);
	}
	
	private final Long FILMID = 1L;
	private final Film FILM = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
	private final Long SHOWID = 2L;
	private final TVShow SHOW = new TVShow(2L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
	private final WatchList ENTRY1 = new WatchList(1L, FILM, null, "The Lord of the Rings: The Fellowship of the Ring", "Complete", 1 , 1, "Fantasy", 10);
	private final WatchList ENTRY2 = new WatchList(2L, null, SHOW, "Gintama", "Watching", 367 , 100, "Comedy/Action", 10);
	
	@Test
	public void testGetAll() {
		List<WatchListDTO> expected = List.of(mapToDTO(ENTRY1), mapToDTO(ENTRY2));
		try {

			mock.perform(get("/api/list/getAll").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetEntry() {
		Long id = 1L;
		WatchListDTO expected = mapToDTO(ENTRY1);
		try {

			mock.perform(get("/api/list/getEntry/"+id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByName() {
		String name = ENTRY2.getName();
		List<WatchListDTO> expected = List.of(mapToDTO(ENTRY2));
		try {

			mock.perform(get("/api/list/search/"+name).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByStatus() {
		String status = ENTRY2.getStatus();
		List<WatchListDTO> expected = List.of(mapToDTO(ENTRY2));
		try {

			mock.perform(get("/api/list/searchBy?status="+status+"&genre=&rating=0").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByGenre() {
		String genre = ENTRY2.getGenre();
		List<WatchListDTO> expected = List.of(mapToDTO(ENTRY2));
		try {

			mock.perform(get("/api/list/searchBy?status=&genre="+genre+"&rating=0").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByRating() {
		int rating = 10;
		List<WatchListDTO> expected = List.of(mapToDTO(ENTRY1), mapToDTO(ENTRY2));
		try {

			mock.perform(get("/api/list/searchBy?status=&genre=&rating="+rating).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByEmpty() {
		
		List<WatchListDTO> expected = List.of();
		try {

			mock.perform(get("/api/list/searchBy?status=&genre=&rating=-1").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		Long id = 1L;
		WatchList update = ENTRY1;
		try {

			mock.perform(put("/api/list/update/"+id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.content(this.jsonifier.writeValueAsString(update)))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(update)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		Long id = 1L;
		boolean expected = true;
		try {

			mock.perform(delete("/api/list/delete/"+id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
