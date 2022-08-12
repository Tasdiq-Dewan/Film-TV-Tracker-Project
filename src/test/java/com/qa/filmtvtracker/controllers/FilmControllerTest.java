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
import com.qa.filmtvtracker.main.FilmTvTrackerProjectApplication;

@SpringBootTest(classes= {FilmTvTrackerProjectApplication.class, FilmController.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
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
	private final Film TEST_FILM3 = new Film(3L, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
	
	private final Long TEST_CREATEID  = 3L;
	private final Film TEST_CREATEFILM = new Film(null, "Batman", "Tim Burton", (short) 1989, "Superhero", 126);
	
//	@BeforeEach
//	public void dbWipe(){
//		
//	}
	
	@Test
	public void testCreate() {
		Film expected = TEST_CREATEFILM;
		expected.setFilmId(TEST_CREATEID);
		try {

			mock.perform(post("/api/films/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.content(this.jsonifier.writeValueAsString(TEST_CREATEFILM)))

					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(this.mapToDTO(expected))));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAll() {
		List<FilmDTO> expected = List.of(mapToDTO(TEST_FILM1), mapToDTO(TEST_FILM2));
		try {

			mock.perform(get("/api/films/getAll").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetFilm() {
		FilmDTO expected = mapToDTO(TEST_FILM1);
		
		try {

			mock.perform(get("/api/films/getFilm/"+TEST_ID1).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchName() {
		List<FilmDTO> expected = List.of(mapToDTO(TEST_FILM2));
		String name = TEST_FILM2.getFilmName();
		try {

			mock.perform(get("/api/films/search/"+name).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByDirector() {
		List<FilmDTO> expected = List.of(mapToDTO(TEST_FILM2));
		String director = TEST_FILM2.getDirector();
		try {

			mock.perform(get("/api/films/searchBy?director="+director+"&genre=&year=0").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByGenre() {
		List<FilmDTO> expected = List.of(mapToDTO(TEST_FILM2));
		String genre = TEST_FILM2.getGenre();
		try {

			mock.perform(get("/api/films/searchBy?director=&genre="+genre+"&year=0").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByYear() {
		List<FilmDTO> expected = List.of(mapToDTO(TEST_FILM2));
		short year = TEST_FILM2.getYearRelease();
		try {

			mock.perform(get("/api/films/searchBy?director=&genre=&year="+year).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchByEmpty() {
		List<FilmDTO> expected = List.of();
		try {

			mock.perform(get("/api/films/searchBy?director=&genre=&year=0").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		Film updated = TEST_FILM1;
		Long id = TEST_ID1;
		try {

			mock.perform(put("/api/films/updateFilm/"+id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
					.content(this.jsonifier.writeValueAsString(updated)))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(updated)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete() {
		Long id = TEST_ID2;
		boolean expected = true;
		try {

			mock.perform(delete("/api/films/delete/"+id).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(content().json(this.jsonifier.writeValueAsString(expected)));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
