package com.qa.filmtvtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qa.filmtvtracker.entities.Film;
import com.qa.filmtvtracker.entities.FilmDTO;
import com.qa.filmtvtracker.services.FilmService;

@Service
@RestController
@RequestMapping("/api/films")
public class FilmController {
	
	private FilmService service;

	@Autowired
	public FilmController(FilmService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/getAll")
	public List<FilmDTO> getAll(){
		return this.service.getAllFilms();
	}
	
	@GetMapping("/getFilm/{id}")
	public FilmDTO get(@PathVariable Long id) {
		return this.service.getFilm(id);
	}
	
	@PostMapping("/add")
	public Film create(@RequestBody Film film) {
		return this.service.addFilm(film);
	}
	
	@GetMapping("/search/{name}")
	public List<FilmDTO> search(@PathVariable String name){
		return this.service.searchFilmsByName(name);
	}
	
	@GetMapping("/searchBy")
	public List<FilmDTO> searchBy(@RequestParam String director, @RequestParam String genre, @RequestParam short year){
		if(director != null) {
			return this.service.searchFilmsByDirector(director);
		}
		else if(genre != null) {
			return this.service.searchFilmsByGenre(genre);
		}
		else {
			return this.service.searchFilmsByYear(year);
		}
	}
	
	@PutMapping("/updateFilm/{id}")
	public Film update(@RequestBody Film film, @PathVariable Long id) {
		return this.service.updateFilm(id, film);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.removeFilm(id);
	}

}
