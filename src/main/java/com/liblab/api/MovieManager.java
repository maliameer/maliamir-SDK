package com.liblab.api;

import java.io.IOException;

import java.util.List;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;

import com.liblab.rest.RESTCommunication;
import com.liblab.rest.RESTServices;

import com.liblab.api.model.Quote;
import com.liblab.api.model.Quotes;
import com.liblab.api.model.Movie;
import com.liblab.api.model.Movies;

public class MovieManager extends Manager {

	private static Logger logger = Logger.getLogger(MovieManager.class.getName());

	public String getMainEndpoint() {
		return RESTServices.MOVIE;
	}

	public List<Movie> getMovies(String queryString, String sort, int limit, int page, int offset) {
		Movies movies = (Movies)super.get(Movies.class, queryString, sort, limit, page, offset);
		return movies.getMovies();
	}

	public List<Movie> getMovies() {
		
		Call<Movies> moviesRestCall = RESTCommunication.getRESTServicesInstance().getMovies(Manager.getBearerAuthorization());
		try {
			return moviesRestCall.execute().body().getMovies();
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Movies due to - " + ioe.getMessage());
			return null;
		}

	}

	public Movie getMovie(String id) {
		
		Call<Movies> moviesRestCall = RESTCommunication.getRESTServicesInstance().getMovie(Manager.getBearerAuthorization(), id);
		try {
			List<Movie> movies = moviesRestCall.execute().body().getMovies();
			return movies != null && movies.size() > 0 ? movies.get(0) : null;
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Movie due to - " + ioe.getMessage());
			return null;
		}
		
	}

	public List<Quote> getMovieQuotes(String id) {
		
		Call<Quotes> movieQuotesRestCall = RESTCommunication.getRESTServicesInstance().getMovieQuotes(Manager.getBearerAuthorization(), id);
		try {
			return movieQuotesRestCall.execute().body().getQuotes();
		} catch(IOException ioe) {
			logger.log(Level.SEVERE, "Error while loading Movie Quote due to - " + ioe.getMessage());
			return null;
		}

	}

}