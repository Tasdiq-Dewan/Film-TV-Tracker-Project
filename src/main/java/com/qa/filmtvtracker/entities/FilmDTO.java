package com.qa.filmtvtracker.entities;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
	
	@Getter
	@Setter
	private Long filmId;
	
	@Getter
	@Setter
	private String filmName;
	
	@Getter
	@Setter
	private String director;
	
	@Getter
	@Setter
	private Date yearRelease;
	
	@Getter
	@Setter
	private String genre;
	
	@Getter
	@Setter
	private int runtime;
}
