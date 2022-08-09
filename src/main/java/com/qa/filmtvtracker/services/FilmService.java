package com.qa.filmtvtracker.services;

import java.util.List;
import java.util.Optional;
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
	
	public List<FilmDTO> searchFilmsByName(String name){
		return this.repo.findFilmByFilmName(name).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<FilmDTO> searchFilmsByYear(short year){
		return this.repo.findFilmByYearRelease(year).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<FilmDTO> searchFilmsByGenre(String genre){
		return this.repo.findFilmByGenre(genre).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<FilmDTO> searchFilmsByDirector(String director){
		return this.repo.findFilmByDirector(director).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public Film updateFilm(Long id, Film film) {
		Film toSave = this.repo.findById(id).orElseThrow(FilmNotFoundException::new);
		toSave.setFilmName(film.getFilmName());
		toSave.setDirector(film.getDirector());
		toSave.setGenre(film.getGenre());
		toSave.setYearRelease(film.getYearRelease());
		toSave.setRuntime(film.getRuntime());
		return this.repo.save(toSave);
	}
	
	public boolean removeFilm(Long id) throws FilmNotFoundException {
		if(!this.repo.existsById(id)){
			throw new FilmNotFoundException();
		}
		else {
			this.repo.deleteById(id);
			return !this.repo.existsById(id);
		}
	}
}
