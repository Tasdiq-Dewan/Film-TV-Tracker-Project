package com.qa.filmtvtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.filmtvtracker.entities.TVShow;

@Repository
public interface TVShowRepo extends JpaRepository<TVShow, Long> {
	List<TVShow> findTVShowByShowName(String name);
	List<TVShow> findTVShowByGenre(String genre);
	List<TVShow> findTVShowByYearBegan(short year);
	List<TVShow> findTVShowByEpisodes(int episodes);
	List<TVShow> findTVShowBySeasons(int seasons);
}
