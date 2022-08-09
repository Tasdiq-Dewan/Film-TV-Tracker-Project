package com.qa.filmtvtracker.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@OneToOne
	private Long filmId;
	
	@Column(nullable = false)
	private String filmName;
	
	@Column(nullable = false)
	private String director;
	
	@Column(nullable = false)
	private Date year;
	
	@Column(nullable = false)
	private String genre;
	
	@Column(nullable = false)
	private int runtime;
}
