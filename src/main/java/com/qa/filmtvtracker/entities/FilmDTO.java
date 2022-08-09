package com.qa.filmtvtracker.entities;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilmDTO {

	private Long filmId;
	
	private String filmName;
	
	private String director;
	
	private Date yearRelease;
	
	private String genre;
	
	private int runtime;
}
