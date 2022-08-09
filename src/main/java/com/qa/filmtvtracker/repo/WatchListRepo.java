package com.qa.filmtvtracker.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.filmtvtracker.entities.WatchList;

public interface WatchListRepo extends JpaRepository<WatchList, Long> {
	List<WatchList> findWatchListByName(String name);
	List<WatchList> findWatchListByGenre(String genre);
	List<WatchList> findWatchListByStatus(String status);
	List<WatchList> findWatchListByRating(int rating);
}
