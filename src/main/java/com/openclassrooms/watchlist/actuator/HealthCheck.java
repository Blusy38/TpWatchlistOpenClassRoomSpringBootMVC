package com.openclassrooms.watchlist.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.openclassrooms.watchlist.service.MovieInfoService;

@Component 
public class HealthCheck implements HealthIndicator {

	
	private MovieInfoService movieRatingService;

	@Autowired
	public HealthCheck(MovieInfoService movieRatingService) {
		super();
		this.movieRatingService = movieRatingService;
	}
	
	public Health health() {
		
		if (movieRatingService.getMovieInfoByTitle("UP")==null) {
			return Health.down().withDetail("Cause", "OMDb API is not available").build();
		}
		return Health.up().build();
	}

}