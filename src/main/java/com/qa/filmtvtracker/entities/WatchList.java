package com.qa.filmtvtracker.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WatchList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "film_id", referencedColumnName = "film_id")
	private Film film;
	
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "show_id", referencedColumnName = "show_id")
	private TVShow tvShow;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private int total;
	
	@Column
	private int progress;
	
	@Column(nullable = false)
	private String genre;
	
	@Column(nullable = false)
	private int rating;
}
