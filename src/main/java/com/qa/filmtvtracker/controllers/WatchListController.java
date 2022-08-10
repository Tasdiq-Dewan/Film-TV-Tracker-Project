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

import com.qa.filmtvtracker.entities.Film;
import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.entities.WatchList;
import com.qa.filmtvtracker.entities.WatchListDTO;
import com.qa.filmtvtracker.services.WatchListService;

@Service
@RestController
@RequestMapping("/api/list")
public class WatchListController {
	
	private WatchListService service;

	@Autowired
	public WatchListController(WatchListService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/getAll")
	public List<WatchListDTO> getAll(){
		return this.service.getAllWatchListEntries();
	}
	
	@GetMapping("/getEntry/{id}")
	public WatchListDTO get(@PathVariable Long id) {
		return this.service.getEntry(id);
	}
	
	@PostMapping("/addFilm")
	public WatchList addFilm(@RequestBody Film film, @RequestParam String status, @RequestParam int progress) {
		return this.service.addFilmEntry(film, status, progress);
	}
	
	@PostMapping("/addShow")
	public WatchList addShow(@RequestBody TVShow show, String status, int progress) {
		return this.service.addShowEntry(show, status, progress);
	}
	
	@GetMapping("/search/{name}")
	public List<WatchListDTO> search(@PathVariable String name){
		return this.service.searchEntryByName(name);
	}
	
	@GetMapping("/searchBy")
	public List<WatchListDTO> searchBy(@RequestParam String status, @RequestParam String genre, @RequestParam int rating){
		if(!(status.equals(null) || status.equals(""))) {
			return this.service.searchEntryByStatus(status);
		}
		else if(!(genre.equals(null) || genre.equals(""))) {
			return this.service.searchEntryByGenre(genre);
		}
		else if(rating >= 0) {
			return this.service.searchEntryByRating(rating);
		}
		else {
			return new ArrayList<WatchListDTO>();
		}
	}
	
	@PutMapping("/update/{id}")
	public WatchList update(@RequestBody WatchList entry, @PathVariable Long id) {
		return this.service.updateEntry(id, entry);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable Long id) {
		return this.service.removeEntry(id);
	}
	
}
