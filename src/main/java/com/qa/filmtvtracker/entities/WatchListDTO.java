package com.qa.filmtvtracker.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class WatchListDTO {

	@Getter
	@Setter
	private Long id;
	
	@Setter
	private Film film;
	
	@Setter
	private TVShow tvShow;
	
	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private String status;
	
	@Getter
	@Setter
	private int total;
	
	@Getter
	@Setter
	private int progress;
	
	@Getter
	@Setter
	private String genre;
	
	@Getter
	@Setter
	private int rating;
	
	
}
