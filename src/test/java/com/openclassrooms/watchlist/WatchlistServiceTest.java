package com.openclassrooms.watchlist;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repository.WatchlistRepository;
import com.openclassrooms.watchlist.service.MovieInfoService;
import com.openclassrooms.watchlist.service.WatchlistService;

@RunWith(MockitoJUnitRunner.class)

public class WatchlistServiceTest {
	@InjectMocks
	private WatchlistService watchlistService;

	@Mock
	private WatchlistRepository watchlistRepositoryMock;

	@Mock
	private MovieInfoService movieInfoServiceMock;

	@Test
	public void testGetWatchlistItemsReturnsAllItemsFromRepository() {
		//Arrange
		//WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M" , "" );
		//WatchlistItem item2 = new WatchlistItem("Star Treck", "8.8", "M" , "");
		//List<WatchlistItem> mockItems = Arrays.asList(item1, item2);

		//when(watchlistRepositoryMock.findAll()).thenReturn(mockItems);

		//Act
		List<WatchlistItem> result = watchlistService.getWatchlistItems("title","ASC");

		//Assert
		assertTrue(result.size() == 2);
		assertTrue(result.get(0).getTitle().equals("Star Wars"));
		assertTrue(result.get(1).getTitle().equals("Star Treck"));
	}
	@Test
	public void testGetwatchlistItemsRatingFormOmdbServiceOverrideTheValueInItems() {

		//Arrange
		//WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M" , "");
		//List<WatchlistItem> mockItems = Arrays.asList(item1);

		//when(watchlistRepositoryMock.findAll()).thenReturn(mockItems);	
		when(movieInfoServiceMock.getMovieRating(movieInfoServiceMock.getMovieInfoByTitle(any(String.class)))).thenReturn("10");
		

		//Act
		List<WatchlistItem> result = watchlistService.getWatchlistItems("title","ASC");

		//Assert
		assertTrue(result.get(0).getRating().equals("10"));
	}
	@Test
	public void testgetWatchlistItemsSize() {
		//Arrange
		//WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M" , "");
		//WatchlistItem item2 = new WatchlistItem("Star Treck", "8.8", "M" , "");
		//List<WatchlistItem> mockItems = Arrays.asList(item1,item2);

		//when(watchlistRepositoryMock.findAll()).thenReturn(mockItems);	
		
		//Act
		int result = (int) watchlistRepositoryMock.count();

		//Assert
		assertTrue(result == 2);
	}


}
