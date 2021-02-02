package com.movie.directory.movies.demo.service;

import java.util.HashMap;
import java.util.List;

import com.movie.directory.movies.demo.entity.Rater;

public interface RaterService {
	
	public List<Rater> findAll();
	
	public Rater findById(int theId);
	
	public void save (Rater theMovie);
	
	public void deleteRater(int theId);
	
//	public HashMap<String,Double> printAverageRatings(int minimalRaters);
//	
//	public void printAverageRatingsByYearAfterAndGenre();
//	
//	public void MovieRunnerSimilarRatings();
//	
//	public void printSimilarRatingsByGenre ();
//	
//	public void printSimilarRatingsByDirector ();
//	
//	public void printSimilarRatingsByGenreAndMinutes();
//	
//	public void printSimilarRatingsByYearAfterAndMinutes ();

}
