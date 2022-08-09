package com.qa.filmtvtracker.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TVShow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long showId;
	
	@Column(nullable = false)
	private String showName;
	
	@Column(nullable = false)
	private Date yearBegan;
	
	@Column
	private Date yearEnded;
	
	@Column(nullable = false)
	private String genre;
	
	@Column(nullable = false)
	private int episodes;
	
	@Column(nullable = false)
	private int seasons;
}
