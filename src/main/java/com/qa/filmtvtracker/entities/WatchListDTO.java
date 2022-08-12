package com.qa.filmtvtracker.entities;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(film, genre, id, name, progress, rating, status, total, tvShow);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WatchListDTO other = (WatchListDTO) obj;
		return Objects.equals(film, other.film) && Objects.equals(genre, other.genre) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && progress == other.progress && rating == other.rating
				&& Objects.equals(status, other.status) && total == other.total && Objects.equals(tvShow, other.tvShow);
	}
	
	
}
