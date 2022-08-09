package com.qa.filmtvtracker.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.entities.TVShowDTO;
import com.qa.filmtvtracker.exceptions.TVShowNotFoundException;
import com.qa.filmtvtracker.repo.TVShowRepo;

@Service
public class TVShowService {

	private TVShowRepo repo;
	
	private ModelMapper mapper;

	@Autowired
	public TVShowService(TVShowRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private TVShowDTO mapToDTO(TVShow show) {
		return mapper.map(show, TVShowDTO.class);
	}
	
	public TVShow addShow(TVShow show) {
		return this.repo.save(show);
	}
	
	public List<TVShowDTO> getAllShows(){
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public TVShowDTO getShow(Long id) {
		TVShow show = this.repo.findById(id).orElseThrow(TVShowNotFoundException::new);
		return mapToDTO(show);
	}
	
	public List<TVShowDTO> searchShowsByName(String name){
		return this.repo.findTVShowByShowName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<TVShowDTO> searchShowsByGenre(String genre){
		return this.repo.findTVShowByGenre(genre).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<TVShowDTO> searchShowsByYearBegan(short year){
		return this.repo.findTVShowByYearBegan(year).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<TVShowDTO> searchShowsByEpisodes(int episodes){
		return this.repo.findTVShowByEpisodes(episodes).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<TVShowDTO> searchShowsBySeasons(int seasons){
		return this.repo.findTVShowBySeasons(seasons).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public TVShow updateShow(Long id, TVShow show) {
		TVShow toSave = this.repo.findById(id).orElseThrow(TVShowNotFoundException::new);
		toSave.setShowName(show.getShowName());
		toSave.setGenre(show.getGenre());
		toSave.setEpisodes(show.getEpisodes());
		toSave.setSeasons(show.getSeasons());
		toSave.setYearBegan(show.getYearBegan());
		toSave.setYearEnded(show.getYearBegan());
		return this.repo.save(toSave);
	}
	
	public boolean removeShow(Long id) {
		if(!this.repo.existsById(id)){
			throw new TVShowNotFoundException();
		}
		else {
			this.repo.deleteById(id);
			return !this.repo.existsById(id);
		}
	}
}
