package com.openclassrooms.watchlist.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.openclassrooms.watchlist.controller.WatchlistController;
import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repository.WatchlistRepository;

@Service
public class WatchlistService {

	Logger logger = LoggerFactory.getLogger(WatchlistController.class);

	private List<WatchlistItem> watchlistItems = new ArrayList<WatchlistItem>();


	private MovieInfoService movieInfoService;

	@Autowired
	private WatchlistRepository watchlistRepository;

	@Autowired
	public WatchlistService(MovieInfoService movieInfoService) {
		super();
		this.movieInfoService = movieInfoService;
	}

	public List<WatchlistItem> getWatchlistItems(String order, String dir){
		logger.warn("CALL : getWatchlistItems " + order + " " + dir);
		if (dir.contains("ASC")) {
			List<WatchlistItem> watchlistItems = watchlistRepository.findAll(Sort.by(Sort.Direction.ASC, order));
			logger.warn("getWatchlistItems ASC " + order + " " + dir);
			return watchlistItems;
		}

		if (dir.contains("DESC")) {
			List<WatchlistItem> watchlistItems = watchlistRepository.findAll(Sort.by(Sort.Direction.DESC, order));
			logger.warn("getWatchlistItems DESC " + order + " " + dir);
			return watchlistItems;

		}
		return watchlistItems;
	}

	public void addWatchlistItem(WatchlistItem watchlistItem) {

		logger.warn("Watchlist Service addWatchlistItem called with :" + watchlistItem.getTitle());

		ObjectNode title = movieInfoService.getMovieInfoByTitle(watchlistItem.getTitle());
		
		logger.warn("Watchlist Service addWatchlistItem title :" + title);
		
		
		if (movieInfoService.getMovieResponse(title)=="True") {
		
		watchlistItem.setTitle(movieInfoService.getMovieTitle(title));
		watchlistItem.setActors(movieInfoService.getMovieActors(title));
		watchlistItem.setComment(movieInfoService.getMovieComment(title));
		watchlistItem.setGenre(movieInfoService.getMovieGenre(title));
		watchlistItem.setPoster(movieInfoService.getMoviePoster(title));
		watchlistItem.setRating(movieInfoService.getMovieRating(title));
		watchlistItem.setReleased(movieInfoService.getMovieReleased(title));
		watchlistItem.setRuntime(movieInfoService.getMovieRuntime(title));
		watchlistItem.setType(movieInfoService.getMovieType(title));
		watchlistItem.setImdbID(movieInfoService.getMovieImdbID(title));
		watchlistItem.setTotalSeasons(movieInfoService.getMovieTotalSeasons(title));
		} 
		watchlistRepository.save(watchlistItem);
	}
	
	public void updateWatchlistItem(int id, String imdbID){
		logger.warn("UpdateWatchlistItem Watchlist Service called ");

		WatchlistItem watchlistItem =  watchlistRepository.findById(id);
				
		logger.warn("UpdateWatchlistItem Watchlist Service search :" + imdbID);

		logger.warn("UpdateWatchlistItem Watchlist Service Update :" + watchlistItem.getImdbID() + " to " + imdbID);
		ObjectNode imdbIDNode = movieInfoService.getMovieInfoByImdbID(imdbID);
		watchlistItem.setTitle(movieInfoService.getMovieTitle(imdbIDNode));
		watchlistItem.setActors(movieInfoService.getMovieActors(imdbIDNode));
		watchlistItem.setComment(movieInfoService.getMovieComment(imdbIDNode));
		watchlistItem.setGenre(movieInfoService.getMovieGenre(imdbIDNode));
		watchlistItem.setPoster(movieInfoService.getMoviePoster(imdbIDNode));
		watchlistItem.setRating(movieInfoService.getMovieRating(imdbIDNode));
		watchlistItem.setReleased(movieInfoService.getMovieReleased(imdbIDNode));
		watchlistItem.setRuntime(movieInfoService.getMovieRuntime(imdbIDNode));
		watchlistItem.setType(movieInfoService.getMovieType(imdbIDNode));
		watchlistItem.setImdbID(imdbID);
		watchlistItem.setTotalSeasons(movieInfoService.getMovieTotalSeasons(imdbIDNode));

		watchlistRepository.save(watchlistItem);
	}
	public void updateWatchedStatu(int id, int watched) {
		logger.warn("Watchlist Service updateWatchedSatut called");

		WatchlistItem watchlistItem =  watchlistRepository.findById(id);
		watchlistItem.setWatched(watched);
		watchlistRepository.save(watchlistItem);
	}
	
	public void updateName(int id, String newTitle) {
		logger.warn("Watchlist Service updateName called");

		WatchlistItem watchlistItem =  watchlistRepository.findById(id);
		watchlistItem.setTitle(newTitle);
		watchlistRepository.save(watchlistItem);
	}

	public void deleteMovie(int id) {
		logger.warn("Watchlist Service delete called");

		WatchlistItem watchlistItem =  watchlistRepository.findById(id);
		watchlistRepository.delete(watchlistItem);
		
	}

}
