package com.qa.filmtvtracker.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "film_id", referencedColumnName = "filmId", nullable = true)
	private Film film;
//	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "show_id", referencedColumnName = "showId", nullable = true)
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
	
//	public WatchList(Film film, TVShow show, String name, String status, int progress, int rating) {
//		this.film = film;
//		this.tvShow = show;
//		this.name = name;
//		this.status = status;
//		if(film != null) {
//			this.total = 1;
//		}
//		else if(show != null) {
//			this.total = show.getEpisodes();
//		}
//		else {
//			total = 0;
//		}
//		this.setProgress(progress);
//		this.setRating(rating);
//	}
	
	public void setProgress(int i) {
		if(i >= this.total) {
			this.progress = total;
			setStatus("Complete");
		}
		else {
			this.progress = i;
		}
	}
	
	public void setTotal() {
		if(this.film != null) {
			this.total = 1;
		}
		else if(this.tvShow != null){
			this.total = this.tvShow.getEpisodes();
		}
	}
	
	public void setRating(int i) throws IllegalArgumentException
	{
		if(i >= 0 && i <= 10) {
			this.rating = i;
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}
