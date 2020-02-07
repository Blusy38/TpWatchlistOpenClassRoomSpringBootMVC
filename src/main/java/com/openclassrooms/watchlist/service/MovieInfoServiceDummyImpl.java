package com.openclassrooms.watchlist.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@ConditionalOnProperty(name = "app.environment", havingValue = "dev")
@Service
public class MovieInfoServiceDummyImpl implements MovieInfoService {
	Logger logger = LoggerFactory.getLogger(MovieInfoServiceImpl.class);

	
	String apiUrl = "http://www.omdbapi.com/?apikey=be0ae53&t=";
	@Override
	public ObjectNode getMovieInfoByTitle(String title) {
		
		try {
			RestTemplate template = new RestTemplate();
			
			logger.info("Calling omdbapi with url {}{}", apiUrl , title);
			
			ResponseEntity<ObjectNode> response = 
					template.getForEntity(apiUrl + title, ObjectNode.class);
			
			ObjectNode jsonObject = response.getBody();
			
			logger.debug("OMDb API response : {}",jsonObject);
			
			return jsonObject;
		} catch (Exception e) {
			logger.warn("Something went wront while calling OMDb API" + e.getMessage());
			return null;
		}
	}
	
	public String getMovieComment(ObjectNode jsonObject) {
		return jsonObject.path("Plot").asText();
		
	}

	@Override
	public String getMovieRating(ObjectNode jsonObject) {
		return jsonObject.path("Plot").asText();
	}

	@Override
	public String getMovieActors(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovieGenre(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMoviePoster(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovieReleased(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovieRuntime(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovieType(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMovieTotalSeasons(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return (Integer) null;
	}

	@Override
	public String getMovieTitle(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectNode getMovieList(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<?> showMovieList(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovieImdbID(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ObjectNode getMovieInfoByImdbID(String imdbID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMovieResponse(ObjectNode jsonObject) {
		// TODO Auto-generated method stub
		return null;
	}










}
