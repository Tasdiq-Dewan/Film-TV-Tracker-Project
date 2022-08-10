package com.qa.filmtvtracker.controllers;

import java.util.ArrayList;
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

import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.entities.TVShowDTO;
import com.qa.filmtvtracker.services.TVShowService;

@Service
@RestController
@RequestMapping("/api/tvshows")
public class TVShowController {

	private TVShowService service;

	@Autowired
	public TVShowController(TVShowService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/getAll")
	public List<TVShowDTO> getAll(){
		return this.service.getAllShows();
	}
	
	@GetMapping("getShow/{id}")
	public TVShowDTO get(@PathVariable Long id) {
		return this.service.getShow(id);
	}
	
	@PostMapping("/add")
	public TVShow create(@RequestBody TVShow show) {
		return this.service.addShow(show);
	}
	
	@GetMapping("/search/{name}")
	public List<TVShowDTO> search(@PathVariable String name){
		return this.service.searchShowsByName(name);
	}
	
	@GetMapping("/searchBy")
	public List<TVShowDTO> searchBy(@RequestParam int episodes, @RequestParam int seasons, @RequestParam String genre, @RequestParam short year){
		if(episodes > 0) {
			return this.service.searchShowsByEpisodes(episodes);
		}
		else if(seasons > 0) {
			return this.service.searchShowsBySeasons(seasons);
		}
		else if(!(genre.equals("null") || genre.equals(""))) {
			return this.service.searchShowsByGenre(genre);
		}
		else if (year > 0){
			return this.service.searchShowsByYearBegan(year);
		}
		else {
			return new ArrayList<TVShowDTO>();
		}
	}
	
	@PutMapping("/updateShow/{id}")
	public TVShow update(@PathVariable Long id, @RequestBody TVShow show) {
		return this.service.updateShow(id, show);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.removeShow(id);
	}
}
