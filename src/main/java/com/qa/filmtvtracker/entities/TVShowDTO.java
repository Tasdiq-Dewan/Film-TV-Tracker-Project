package com.qa.filmtvtracker.entities;

import java.sql.Date;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(episodes, genre, seasons, showId, showName, yearBegan, yearEnded);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TVShowDTO other = (TVShowDTO) obj;
		return episodes == other.episodes && Objects.equals(genre, other.genre) && seasons == other.seasons
				&& Objects.equals(showId, other.showId) && Objects.equals(showName, other.showName)
				&& yearBegan == other.yearBegan && yearEnded == other.yearEnded;
	}

	@Override
	public String toString() {
		return "TVShowDTO [showId=" + showId + ", showName=" + showName + ", yearBegan=" + yearBegan + ", yearEnded="
				+ yearEnded + ", genre=" + genre + ", episodes=" + episodes + ", seasons=" + seasons + "]";
	}
	
	
}
