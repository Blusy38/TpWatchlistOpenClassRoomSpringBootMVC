package com.openclassrooms.watchlist.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {
	
	Logger logger = LoggerFactory.getLogger(CustomErrorController.class);


	@Override
	public String getErrorPath() {
		return "/error";
	}

	@GetMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Object url = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);


		if (status.equals(404)) {
			logger.error("Error with status code " + status + " happened sur URL " + url + ". Page not found ! Support! Do something about it!");
			return new ModelAndView("error404");
		} else {
			logger.error("Error with status code " + status + " happened. Support! Do something about it!");
			return new ModelAndView("error");
		}

	}
}