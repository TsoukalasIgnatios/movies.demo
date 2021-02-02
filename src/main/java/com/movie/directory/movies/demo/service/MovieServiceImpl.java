package com.movie.directory.movies.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.directory.movies.demo.businessLogic.AllFilters;
import com.movie.directory.movies.demo.businessLogic.DirectorsFilter;
import com.movie.directory.movies.demo.businessLogic.GenreFilter;
import com.movie.directory.movies.demo.businessLogic.MinutesFilter;
import com.movie.directory.movies.demo.businessLogic.Rating;
import com.movie.directory.movies.demo.businessLogic.YearAfterFilter;
import com.movie.directory.movies.demo.dao.MovieRepository;
import com.movie.directory.movies.demo.entity.Movie;

@Service
public class MovieServiceImpl implements MovieService {

	private MovieRepository movieRepository;

	@Autowired
	public MovieServiceImpl(MovieRepository theMovieDAO) {

		movieRepository = theMovieDAO;
	}

	public MovieServiceImpl() {
	}

	private HashMap<String, Double> avgRatings;

	@Override
	public List<Movie> findAll() {

		return movieRepository.findAll();
	}

	@Override
	public Movie findById(int theId) {
		Optional<Movie> result = movieRepository.findById(theId);

		Movie theMovie = null;
		if (result.isPresent()) {
			theMovie = result.get();
		} else {
			throw new RuntimeException("Did not find movie id - " + theId);
		}
		return theMovie;
	}

	@Override
	public void save(Movie theMovie) {
		movieRepository.save(theMovie);
	}

	@Override
	public void deleteMovie(int theId) {
		movieRepository.deleteById(theId);
	}

	@Override
	public HashMap<String, Double> printAverageRatings(int minimalRaters) {
		FourthRatings fR = new FourthRatings();
		ArrayList<Rating> list = fR.getAverageRatings1(minimalRaters);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size() - i - 1; j++) {
				if (list.get(j).compareTo(list.get(j + 1)) > 0) {
					Collections.swap(list, j, j + 1);
				}
			}
		}
		avgRatings = new HashMap<String, Double>();
		for (Rating rating : list) {
			avgRatings.put(MovieDatabase.getTitle(rating.getItem()), rating.getValue());
		}

		return avgRatings;
	}

	public HashMap<String, Double> printAverageRatingsByYearAfterAndGenre1(int Year, String Gernre) {
		AllFilters allF = new AllFilters();
		YearAfterFilter yAF = new YearAfterFilter(Year);
		GenreFilter genF = new GenreFilter(Gernre);
		allF.addFilter(yAF);
		allF.addFilter(genF);
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		FourthRatings fR = new FourthRatings();
		ratings = fR.getAverageRatingsByFilter1(0, allF);
		for (int i = 0; i < ratings.size(); i++) {
			for (int j = 0; j < ratings.size() - i - 1; j++) {
				if (ratings.get(j).compareTo(ratings.get(j + 1)) > 0) {
					Collections.swap(ratings, j, j + 1);
				}
			}
		}
		avgRatings = new HashMap<String, Double>();
		for (Rating rating : ratings) {
			avgRatings.put(MovieDatabase.getTitle(rating.getItem()), rating.getValue());
		}
		return avgRatings;
	}

	@Override
	public HashMap<String, Double> MovieRunnerSimilarRatings(int id, int numSimilarRaters, int minimalRaters) {
		FourthRatings fR = new FourthRatings();
		ArrayList<Integer> movieIds = new ArrayList<Integer>();
		List<Movie> movieslist = movieRepository.findAll();
		for (Movie m : movieslist) {
			movieIds.add(m.getId());
		}
		ArrayList<Rating> list = fR.getSimilarRatings2(id, numSimilarRaters, minimalRaters, movieIds);
		avgRatings = new HashMap<String, Double>();
		for (Rating rating : list) {
			avgRatings.put(MovieDatabase.getTitle(rating.getItem()), rating.getValue());
		}

		return avgRatings;
	}

	
	public HashMap<String, Double> printSimilarRatingsByGenre(int id, int numSimilarRaters, int minimalRaters,
			GenreFilter geF) {
		FourthRatings fR = new FourthRatings();
		ArrayList<Rating> list = fR.getSimilarRatingsByFilter1(id, numSimilarRaters, minimalRaters, geF);
		avgRatings = new HashMap<String, Double>();
		for (Rating rating : list) {
			avgRatings.put(MovieDatabase.getTitle(rating.getItem()), rating.getValue());
		}
		return avgRatings;

	}

	@Override
	public HashMap<String, Double> printSimilarRatingsByDirector(int id, int numSimilarRatings, int minimalRaters,
			DirectorsFilter dirF) {
		FourthRatings fR = new FourthRatings();
		ArrayList<Rating> list = fR.getSimilarRatingsByFilter1(id, numSimilarRatings, minimalRaters, dirF);
		avgRatings = new HashMap<String, Double>();
		for (Rating rating : list) {
			avgRatings.put(MovieDatabase.getTitle(rating.getItem()), rating.getValue());
		}
		return avgRatings;

	}

	@Override
	public HashMap<String, Double> printSimilarRatingsByYearAfterAndMinutes(int id, int numSimilarRatings,
			int minimalRaters, int year, int minMax, int minMin) {
		FourthRatings fR = new FourthRatings();
		YearAfterFilter yearF = new YearAfterFilter(year);
		MinutesFilter minF = new MinutesFilter(minMax, minMin);
		AllFilters allF = new AllFilters();
		allF.addFilter(yearF);
		allF.addFilter(minF);
		avgRatings = new HashMap<String, Double>();
		ArrayList<Rating> list = fR.getSimilarRatingsByFilter1(id, numSimilarRatings, minimalRaters, allF);
		for (Rating r : list) {
			avgRatings.put(MovieDatabase.getTitle(r.getItem()), r.getValue());
		}
		return avgRatings;
	}

}
