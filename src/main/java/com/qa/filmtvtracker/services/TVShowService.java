package com.qa.filmtvtracker.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.entities.TVShowDTO;
import com.qa.filmtvtracker.repo.TVShowRepo;
import com.qa.filmtvtracker.exceptions.TVShowNotFoundException;

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
}
