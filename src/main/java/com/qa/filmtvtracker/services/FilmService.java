package com.qa.filmtvtracker.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.filmtvtracker.entities.Film;
import com.qa.filmtvtracker.entities.FilmDTO;
import com.qa.filmtvtracker.exceptions.FilmNotFoundException;
import com.qa.filmtvtracker.repo.FilmRepo;

@Service
public class FilmService {
	
	private FilmRepo repo;
	
	private ModelMapper mapper;

	@Autowired
	public FilmService(FilmRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private FilmDTO mapToDTO(Film film) {
		return mapper.map(film, FilmDTO.class);
	}
	
	public Film addFilm(Film film) {
		return this.repo.save(film);
	}
	
	public List<FilmDTO> getAllFilms(){
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public FilmDTO getFilm(Long id) {
		Film result = this.repo.findById(id).orElseThrow(FilmNotFoundException::new);
		return mapToDTO(result);
	}
	
}
