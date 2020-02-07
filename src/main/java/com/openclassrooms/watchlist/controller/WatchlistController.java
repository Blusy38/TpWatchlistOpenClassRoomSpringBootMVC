package com.openclassrooms.watchlist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repository.WatchlistRepository;
import com.openclassrooms.watchlist.service.MovieInfoService;
import com.openclassrooms.watchlist.service.WatchlistService;

@RestController
public class WatchlistController {

	Logger logger = LoggerFactory.getLogger(WatchlistController.class);

	@Autowired
	private WatchlistRepository watchlistRepository;

	@Autowired
	private WatchlistService watchlistService;

	@Autowired
	private MovieInfoService movieInfoService;

	//Show List of the movie on http://localhost:8080/watchlist
	@RequestMapping(value = "/watchlist", method = RequestMethod.GET)
	public ModelAndView getWatchlist(@RequestParam(required = false) String order, String dir) {

		logger.info("GET /getWatchlist called");

		String viewName= "watchlist";

		Map<String,Object> model = new HashMap<String,Object>();
		if (order == null) { order = "title";}
		if (dir == null) { dir = "ASC";}
		model.put("watchlistItems", watchlistService.getWatchlistItems(order, dir));
		model.put("numberOfMovies", watchlistRepository.count());

		return new ModelAndView(viewName,model);		
	}

	//Update watched statu of the movie on http://localhost:8080/watchlist?id=1&watched=0
	@RequestMapping(value = "/watchlist", params = {"id" ,"watched"}, method = RequestMethod.GET)
	public ModelAndView getWatchlist(@RequestParam("id") int id, @RequestParam("watched") int watched) {

		logger.info("Watchlist COntroller getWatchlist for update watched statut called");

		watchlistService.updateWatchedStatu(id, watched);
		String viewName= "watchlist";

		Map<String,Object> model = new HashMap<String,Object>();

		model.put("watchlistItems", watchlistService.getWatchlistItems("title", "ASC"));
		model.put("numberOfMovies", watchlistRepository.count());

		return new ModelAndView(viewName,model);		
	}

	//Shows empty watchlistForm to enter a movie name on http://localhost:8080/watchlistForm
	@RequestMapping(value = "/watchlistForm", method = RequestMethod.GET)
	public ModelAndView showWatchlistForm() {

		logger.info("GET /showwatchlistForm without id called");

		String viewName = "watchlistForm";

		Map<String,Object> model = new HashMap<String,Object>();


		model.put("watchlistItem", new WatchlistItem());	

		return new ModelAndView(viewName,model); 
	}

	//Shows watchlistForm with id for update title on http://localhost:8080/watchlistForm
	@RequestMapping(value = "/watchlistForm", params = "id", method = RequestMethod.GET)
	public ModelAndView showWatchlistForm(@RequestParam("id") Integer id) {

		logger.info("GET /showwatchlistForm with id called");

		String viewName = "watchlistForm";

		Map<String,Object> model = new HashMap<String,Object>();

		Optional<WatchlistItem> watchlistItem = watchlistRepository.findById(id);
		model.put("watchlistItem", watchlistItem);

		return new ModelAndView(viewName,model); 
	}

	//Submit watchlistForm to save data on create
	@RequestMapping(value = "/watchlistForm", method = RequestMethod.POST)
	public ModelAndView submitWatchlistForm(@Valid WatchlistItem watchlistItem, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("watchlistForm");
		}

		logger.info("Watchlist controller submitWatchlistForm called");


		logger.info("Watchlist controller submitWatchlistForm call Service addWatchlistItem with " + watchlistItem);

		watchlistService.addWatchlistItem(watchlistItem);

		RedirectView redirect = new RedirectView();
		redirect.setUrl("/watchlist");

		return new ModelAndView(redirect);
	}

	//Shows watchlistSheet on http://localhost:8080/watchlistSheet
	@GetMapping("/watchlistSheet")
	public ModelAndView showWatchlistSheet(@RequestParam(required = true) Integer id) {

		logger.info("GET /showwatchlistSheet called");


		String viewName = "watchlistSheet";

		Map<String,Object> model = new HashMap<String,Object>();

		Optional<WatchlistItem> watchlistItem = watchlistRepository.findById(id);
		model.put("watchlistItem", watchlistItem);
		logger.info("Controler showWatchlistSheet gives : " + watchlistItem);

		return new ModelAndView(viewName,model); 
	}

	//Delete movie from watchlistSheet on http://localhost:8080/watchlistSheet
	@RequestMapping(value = "/watchlistSheet", params = {"id" ,"imdbID"}, method = RequestMethod.GET)
	public ModelAndView showWatchlistSheet(@RequestParam("id") int id, @RequestParam("imdbID") String imdbID) {

		logger.info("Watchlist Controller showwatchlistSheet for delete called");

		watchlistService.deleteMovie(id);
		String viewName= "watchlist";

		Map<String,Object> model = new HashMap<String,Object>();

		model.put("watchlistItems", watchlistService.getWatchlistItems("title", "ASC"));
		model.put("numberOfMovies", watchlistRepository.count());

		return new ModelAndView(viewName,model);	
	}

	//Show the list of the possible movies found with the api on http://localhost:8080/getList?id=6
	@RequestMapping(value = "/getList", params = "id", method = RequestMethod.GET)
	public ModelAndView getSearchlist(@RequestParam("id") int id) throws JsonParseException, JsonMappingException, IOException {
		logger.info("GET /getSearchlist called");

		String viewName= "getList";

		Map<String,Object> model = new HashMap<String,Object>();

		ArrayList<?> watchlistSearch = movieInfoService.showMovieList(movieInfoService.getMovieList(watchlistRepository.findById(id).getTitle()));
		model.put("watchlistSearch", watchlistSearch);

		logger.info("Watchlist COntroller getSearchlist by ID : " + watchlistSearch);
		return new ModelAndView(viewName,model);		
	}

	//Show the list of the possible movies found with the api on http://localhost:8080/getList?id=6
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public ModelAndView submitSearchlist(WatchlistItem watchlistItem){
		logger.info("POST Watchlist controller submitSearchlist called to Update Title");

		watchlistService.updateName(watchlistItem.getId(),watchlistItem.getTitle());
		String viewName= "getList?id=" + watchlistItem.getId();
		RedirectView redirect = new RedirectView();
		logger.info("POST Watchlist controller submitSearchlist Redirect to getList?id=" + watchlistItem.getId());

		redirect.setUrl(viewName);

		return new ModelAndView(redirect);	
	}

	//Update movie on http://localhost:8080/getList?id=6&imdbID=tt0088763
	@RequestMapping(value = "/getList", params = {"id" ,"imdbID"}, method = RequestMethod.GET)
	public ModelAndView getSearchlist(@RequestParam("imdbID") String imdbID,@RequestParam("id") int id){

		logger.info("Watchlist Controller getSearchlist for update Movie Informations called");
		String viewName= "watchlistSheet?id=" + id;	

		logger.info("Watchlist Controller getSearchlist for update Movie Informations");

		watchlistService.updateWatchlistItem(id, imdbID);



		RedirectView redirect = new RedirectView();
		redirect.setUrl(viewName);

		return new ModelAndView(redirect);
	}

}
