package com.qa.filmtvtracker.entities;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class TVShowDTO {
	@Getter
	@Setter
	private Long showId;
	
	@Getter
	@Setter
	private String showName;
	
	@Getter
	@Setter
	private short yearBegan;
	
	@Getter
	@Setter
	private short yearEnded;
	
	@Getter
	@Setter
	private String genre;
	
	@Getter
	@Setter
	private int episodes;
	
	@Getter
	@Setter
	private int seasons;
}
