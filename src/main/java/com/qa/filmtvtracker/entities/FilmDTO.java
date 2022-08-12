package com.qa.filmtvtracker.entities;

import java.sql.Date;
import java.util.Objects;

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
	private short yearRelease;
	
	@Getter
	@Setter
	private String genre;
	
	@Getter
	@Setter
	private int runtime;

	@Override
	public String toString() {
		return "FilmDTO [filmId=" + filmId + ", filmName=" + filmName + ", director=" + director + ", yearRelease="
				+ yearRelease + ", genre=" + genre + ", runtime=" + runtime + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(director, filmId, filmName, genre, runtime, yearRelease);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilmDTO other = (FilmDTO) obj;
		return Objects.equals(director, other.director) && Objects.equals(filmId, other.filmId)
				&& Objects.equals(filmName, other.filmName) && Objects.equals(genre, other.genre)
				&& runtime == other.runtime && yearRelease == other.yearRelease;
	}
}
