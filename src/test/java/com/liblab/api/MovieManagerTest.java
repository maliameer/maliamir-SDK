package com.liblab.api;

import java.util.List;

import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.liblab.rest.RESTServices;

import com.liblab.api.model.Movie;

public class MovieManagerTest {

	private static final String MOVIE_ID = "5cd95395de30eff6ebccde56";
	private static final String MOVIE_NAME = "The Lord of the Rings Series";
	
	private MovieManager movieManager;

	@Before
	public void setup() {
		movieManager = new MovieManager();
	}

	@Test
	public void testMovies() {
		int moviesCount = movieManager.getMovies().size();
		assertEquals("Unexpected Movies Count", 8, moviesCount);
	}

	@Test
	public void testMovieById() {
		Movie movie = movieManager.getMovie(MOVIE_ID);
		assertNotNull("Movie cannot be NULL", movie);
		assertEquals("Unexpected Movie Name", MOVIE_NAME, movie.getName());
	}

	@Test
	public void testMoviesWithCriteria() {		
		List<Movie> movies = movieManager.getMovies("runtimeInMinutes" + URLEncoder.encode(">") + "460", 
				"sort=boxOfficeRevenueInMillions:" + RESTServices.DESC, 10, 1, 0);
		assertEquals("Unexpected Movies Count", 2, movies.size());
		assertEquals("Unexpected Movie Name", MOVIE_NAME, movies.get(1).getName());
	}

}