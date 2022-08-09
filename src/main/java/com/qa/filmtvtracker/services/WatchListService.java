package com.qa.filmtvtracker.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.filmtvtracker.entities.Film;
import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.entities.WatchList;
import com.qa.filmtvtracker.entities.WatchListDTO;
import com.qa.filmtvtracker.exceptions.TVShowNotFoundException;
import com.qa.filmtvtracker.exceptions.WatchListNotFoundException;
import com.qa.filmtvtracker.repo.WatchListRepo;

@Service
public class WatchListService {
	
	private WatchListRepo repo;
	
	private ModelMapper mapper;
	
	@Autowired
	public WatchListService(WatchListRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private WatchListDTO mapToDTO(WatchList entry) {
		return mapper.map(entry, WatchListDTO.class);
	}
	
	public List<WatchListDTO> getAllWatchListEntries(){
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());

	}
	
	public WatchListDTO getEntry(Long id) {
		WatchList entry = this.repo.findById(id).orElseThrow(WatchListNotFoundException::new);
		return mapToDTO(entry);
	}
	
	public WatchList addFilmEntry(Film film, String status, int progress) {
		WatchList entry = new WatchList();
		
		entry.setFilm(film);
		entry.setName(film.getFilmName());
		entry.setStatus(status);
		entry.setTotal(1);
		entry.setProgress(progress);
		entry.setGenre(film.getGenre());
		return this.repo.save(entry);
	}
	
	public WatchList addShowEntry(TVShow show, String status, int progress) {
		WatchList entry = new WatchList();
		
		entry.setTvShow(show);
		entry.setName(show.getShowName());
		entry.setStatus(status);
		entry.setTotal(show.getEpisodes());
		entry.setProgress(progress);
		entry.setGenre(show.getGenre());
		return this.repo.save(entry);
	}
	
	public List<WatchListDTO> searchEntryByName(String name){
		return this.repo.findWatchListByName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<WatchListDTO> searchEntryByGenre(String genre){
		return this.repo.findWatchListByGenre(genre).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<WatchListDTO> searchEntryByStatus(String status){
		return this.repo.findWatchListByStatus(status).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<WatchListDTO> searchEntryByRating(int rating){
		return this.repo.findWatchListByRating(rating).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public WatchList updateEntry(Long id, WatchList entry) {
		WatchList toSave = this.repo.findById(id).orElseThrow(WatchListNotFoundException::new);
		toSave.setFilm(entry.getFilm());
		toSave.setTvShow(entry.getTvShow());
		toSave.setName(entry.getName());
		toSave.setGenre(entry.getGenre());
		toSave.setTotal(entry.getTotal());
		toSave.setProgress(entry.getProgress());
		toSave.setStatus(entry.getStatus());
		toSave.setRating(entry.getRating());
		
		return this.repo.save(toSave);
	}
	
	public boolean removeEntry(Long id) {
		if(!this.repo.existsById(id)){
			throw new WatchListNotFoundException();
		}
		else {
			this.repo.deleteById(id);
			return !this.repo.existsById(id);
		}
	}
}
