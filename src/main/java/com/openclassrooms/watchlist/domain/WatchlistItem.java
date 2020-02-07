package com.openclassrooms.watchlist.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class WatchlistItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@NotBlank( message="Please enter the title")
	private String title;
	
	private String rating="";
	
	private String released;
	
	private String runtime;
	
	private String genre;
	
	private String actors;
	
	private String poster;
	
	private String type;
	
	private String imdbID;
	
	private int totalSeasons;

	public int watched;
	@Column(length = 1500)
	public String comment;
	
	public static int index;
	
	public WatchlistItem() {
		//this.id = index ++;
	}

	public WatchlistItem(@NotBlank(message = "Please enter the title") String title, String rating, String released,
			String runtime, String genre, String actors, String poster, String type, String imdbID, int totalSeasons,@NotBlank( message="Have you seen that movie ?") int watched,
			String comment) {
		super();
		//this.id = index ++;
		
		this.title = title;
		this.rating = rating;
		this.released = released;
		this.runtime = runtime;
		this.genre = genre;
		this.actors = actors;
		this.poster = poster;
		this.type = type;
		this.imdbID = imdbID;
		this.totalSeasons = totalSeasons;
		this.watched = watched;
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public int getTotalSeasons() {
		return totalSeasons;
	}

	public void setTotalSeasons(int totalSeasons) {
		this.totalSeasons = totalSeasons;
	}

	public int getWatched() {
		return watched;
	}

	public void setWatched(int watched) {
		this.watched = watched;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "WatchlistItem [id=" + id + ", title=" + title + ", rating=" + rating + ", released=" + released
				+ ", runtime=" + runtime + ", genre=" + genre + ", actors=" + actors + ", poster=" + poster + ", type="
				+ type + ", imdbID=" + imdbID + ", totalSeasons=" + totalSeasons + ", watched=" + watched + ", comment="
				+ comment + "]";
	}




}