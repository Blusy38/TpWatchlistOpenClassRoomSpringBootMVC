package com.openclassrooms.watchlist.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.watchlist.domain.WatchlistItem;

@Repository
public interface WatchlistRepository extends JpaRepository<WatchlistItem, Integer>{

	WatchlistItem findById(int id);

	WatchlistItem findByTitle(String title);
	
	WatchlistItem findByImdbID(String imdbID);
		
}
