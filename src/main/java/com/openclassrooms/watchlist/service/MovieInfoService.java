package com.openclassrooms.watchlist.service;


import java.util.ArrayList;



import com.fasterxml.jackson.databind.node.ObjectNode;

public interface MovieInfoService {

	ObjectNode getMovieInfoByTitle(String title);
	ObjectNode getMovieInfoByImdbID(String imdbID);
	ObjectNode getMovieList(String title);
	String getMovieResponse(ObjectNode jsonObject);
	String getMovieTitle(ObjectNode jsonObject);
	String getMovieComment(ObjectNode jsonObject);
	String getMovieRating(ObjectNode jsonObject);
	String getMovieActors(ObjectNode jsonObject);
	String getMovieGenre(ObjectNode jsonObject);
	String getMoviePoster(ObjectNode jsonObject);
	String getMovieReleased(ObjectNode jsonObject);
	String getMovieRuntime(ObjectNode jsonObject);
	String getMovieType(ObjectNode jsonObject);
	String getMovieImdbID(ObjectNode jsonObject);
	int getMovieTotalSeasons(ObjectNode jsonObject);
	ArrayList<?> showMovieList(ObjectNode jsonObject);


}