package com.movie.directory.movies.demo.service;

import java.util.HashMap;
import java.util.List;

import com.movie.directory.movies.demo.businessLogic.DirectorsFilter;
import com.movie.directory.movies.demo.businessLogic.GenreFilter;
import com.movie.directory.movies.demo.entity.Movie;

public interface MovieService {
	
	public List<Movie> findAll();
	
	public Movie findById(int theId);
	
	public void save (Movie theMovie);
	
	public void deleteMovie(int theId);
	
	public HashMap<String,Double> printAverageRatings(int minimalRaters);
	
	public HashMap<String,Double> printAverageRatingsByYearAfterAndGenre1(int Year,String Gernre) ;
	
	public HashMap<String,Double> MovieRunnerSimilarRatings(int id, int numSimilarRaters, int minimalRaters);
	
	public HashMap<String, Double> printSimilarRatingsByGenre(int id, int numSimilarRaters, int minimalRaters,
			GenreFilter geF);
	
	public HashMap<String,Double> printSimilarRatingsByDirector(int id,int numSimilarRatings,int minimalRaters,
			DirectorsFilter dirF);
	
	public HashMap<String,Double> printSimilarRatingsByYearAfterAndMinutes (int id,int numSimilarRatings,int minimalRaters,
			int yaer,int minMax,int minMin);
}
