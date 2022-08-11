package com.qa.filmtvtracker.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.filmtvtracker.entities.Film;
import com.qa.filmtvtracker.entities.FilmDTO;
import com.qa.filmtvtracker.main.FilmTvTrackerProjectApplication;

@SpringBootTest(classes= {FilmTvTrackerProjectApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FilmControllerTest {

	@Autowired
	MockMvc mock;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ObjectMapper jsonifier;
	
	private FilmDTO mapToDTO(Film film) {
		return mapper.map(film, FilmDTO.class);
	}
	
	private final Long TEST_ID1 = 1L;
	private final Film TEST_FILM1 = new Film(1L, "The Lord of the Rings: The Fellowship of the Ring", "Peter Jackson", (short) 2001, "Fantasy", 208);
	
	private final Long TEST_ID2 = 2L;
	private final Film TEST_FILM2 = new Film(2L, "The Godfather", "Francis Ford Coppola", (short) 1972, "Mafia", 175);
	
	private final Long TEST_ID3  = 3L;
	private final Film TEST_FILM3 = new Film(null, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
	
	
	
	@Test
	public void testCreate() {
		Film expected = TEST_FILM3;
		expected.setFilmId(TEST_ID3);
		try {

			mock.perform(post("/api/films/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.content(this.jsonifier.writeValueAsString(TEST_FILM3)))

					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDTO(expected))));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAll() {
		List<FilmDTO> expected = List.of(mapToDTO(TEST_FILM1), mapToDTO(TEST_FILM2), mapToDTO(TEST_FILM3));
		try {

			mock.perform(get("/api/films/getAll").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
