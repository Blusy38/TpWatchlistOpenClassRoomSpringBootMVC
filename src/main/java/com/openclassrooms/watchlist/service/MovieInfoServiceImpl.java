package com.openclassrooms.watchlist.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Service
@ConditionalOnProperty(name = "app.environment", havingValue = "prod")

public class MovieInfoServiceImpl implements MovieInfoService {
	
	Logger logger = LoggerFactory.getLogger(MovieInfoServiceImpl.class);

	
	String apiUrlInfoByTitle = "http://www.omdbapi.com/?apikey=be0ae53&plot=full&t=";
	String apiUrlInfoByImdbID = "http://www.omdbapi.com/?apikey=be0ae53&plot=full&i=";

	String apiUrlSearchByTitle = "http://www.omdbapi.com/?apikey=be0ae53&s=";
	
	@Override
	public ObjectNode getMovieInfoByTitle(String title) {
		
		try {
			RestTemplate template = new RestTemplate();
			
			logger.info("getMovieInfoByTitle is Calling omdbapi with url {}{}", apiUrlInfoByTitle , title);
			
			ResponseEntity<ObjectNode> response = 
					template.getForEntity(apiUrlInfoByTitle + title.replace(" -", ""), ObjectNode.class);
			
			ObjectNode jsonObject = response.getBody();
			
			logger.info("OMDb API response : {}",jsonObject);
			
			return jsonObject;
		} catch (Exception e) {
			logger.warn("Something went wront while calling OMDb API" + e.getMessage());
			return null;
		}
	}
	
	@Override
	public ObjectNode getMovieInfoByImdbID(String imdbID) {
		
		try {
			RestTemplate template = new RestTemplate();
			
			logger.info("getMovieInfoByImdbID is Calling omdbapi with url {}{}", apiUrlInfoByImdbID , imdbID);
			
			ResponseEntity<ObjectNode> response = 
					template.getForEntity(apiUrlInfoByImdbID + imdbID, ObjectNode.class);
			
			ObjectNode jsonObject = response.getBody();
			
			logger.debug("OMDb API response : {}",jsonObject);
			
			return jsonObject;
		} catch (Exception e) {
			logger.warn("Something went wront while calling OMDb API" + e.getMessage());
			return null;
		}
	}
	
	@Override
	public ObjectNode getMovieList(String title) {
		
		try {
			RestTemplate template = new RestTemplate();
			
			logger.info("getMovieList is Calling omdbapi with url {}{}", apiUrlSearchByTitle , title.replace(" -", ""));
			
			ResponseEntity<ObjectNode> response = 
					template.getForEntity(apiUrlSearchByTitle + title.replace(" -", ""), ObjectNode.class);
			
			ObjectNode jsonObject = response.getBody();
					
			logger.debug("OMDb API response : {}",jsonObject);
			
			return jsonObject;
		} catch (Exception e) {
			logger.warn("Something went wront while calling OMDb API" + e.getMessage());
			return null;
		}
		
		
	}
	
	public ArrayList<?> showMovieList(ObjectNode jsonObject) {
		
		JsonNode jsonnode = jsonObject.path("Search");
		logger.info("MvieServiceImpl showMovieList pure {}",jsonnode);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonnode = mapper.readTree(jsonnode.toString());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<?> watchlistSearch = mapper.convertValue(jsonnode, ArrayList.class);

		logger.info("MvieServiceImpl showMovieList withArray toString {}", watchlistSearch);

		return watchlistSearch;		
	}
	
	public String getMovieResponse(ObjectNode jsonObject) {
		return jsonObject.path("Response").asText();		
	}
	
	public String getMovieTitle(ObjectNode jsonObject) {
		return jsonObject.path("Title").asText();		
	}
	
	public String getMovieActors(ObjectNode jsonObject) {
		return jsonObject.path("Actors").asText();		
	}
	
	public String getMovieComment(ObjectNode jsonObject) {
		return jsonObject.path("Plot").asText();		
	}
	
	public String getMovieGenre(ObjectNode jsonObject) {
		return jsonObject.path("Genre").asText();		
	}
	
	public String getMoviePoster(ObjectNode jsonObject) {
		return jsonObject.path("Poster").asText();		
	}
	
	public String getMovieRating(ObjectNode jsonObject) {
		return jsonObject.path("imdbRating").asText();		
	}
	
	public String getMovieReleased(ObjectNode jsonObject) {
		return jsonObject.path("Released").asText();		
	}
	
	public String getMovieRuntime(ObjectNode jsonObject) {
		return jsonObject.path("Runtime").asText();		
	}
	
	public String getMovieType(ObjectNode jsonObject) {
		return jsonObject.path("Type").asText();		
	}
	
	public String getMovieImdbID(ObjectNode jsonObject) {
		return jsonObject.path("imdbID").asText();		
	}
	
	public int getMovieTotalSeasons(ObjectNode jsonObject) {
		return jsonObject.path("totalSeasons").asInt();		
	}
}
