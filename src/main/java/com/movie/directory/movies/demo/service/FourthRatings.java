package com.movie.directory.movies.demo.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.directory.movies.demo.businessLogic.AllFilters;
import com.movie.directory.movies.demo.businessLogic.Filter;
import com.movie.directory.movies.demo.businessLogic.Rating;
import com.movie.directory.movies.demo.businessLogic.TrueFilter;
import com.movie.directory.movies.demo.dao.MovieRepository;
import com.movie.directory.movies.demo.entity.Rater;

@Service
public class FourthRatings {
	private MovieRepository movieRepository;

	@Autowired
	public FourthRatings(MovieRepository theMovieRepository) {
		movieRepository = theMovieRepository;
	}

	public FourthRatings() {
	}

	private double getAverageByID(int movieID, int minimalRaters) {
		double sum = 0.0;
		double countRates = 0.0;

		for (Rater rater : RaterDatabase.getRaters()) {
			if (rater.hasRating(movieID)) {
				countRates++;
				sum += rater.getRating(movieID);
			}
		}

		if (countRates > minimalRaters) {
			return sum / countRates;
		}

		return 0.0;
	}

	public ArrayList<Rating> getAverageRatings1(int minimalRaters) {

		ArrayList<Integer> movieIds = new ArrayList<Integer>();
		movieIds = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
		for (int movie : movieIds) {
			double avgRating = getAverageByID(movie, minimalRaters);
			if (avgRating != 0.0) {
				Rating rating = new Rating(movie, avgRating);
				avgRatingList.add(rating);
			}
		}
		return avgRatingList;
	}

	public ArrayList<Rating> getAverageRatings(int minimalRaters, ArrayList<Integer> movieIds) {

		ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
		for (int movie : movieIds) {
			double avgRating = getAverageByID(movie, minimalRaters);
			if (avgRating != 0.0) {
				Rating rating = new Rating(movie, avgRating);
				avgRatingList.add(rating);
			}
		}
		return avgRatingList;
	}

	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, AllFilters allF,
			ArrayList<Integer> movieIds) {
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		for (int movie : movieIds) {
			double avgRating = getAverageByID(movie, minimalRaters);
			if (avgRating != 0.0) {
				if (allF.satisfies(movie)) {
					Rating rating = new Rating(movie, avgRating);
					ratings.add(rating);
				}
			}
		}

		return ratings;
	}

	public ArrayList<Rating> getAverageRatingsByFilter1(int minimalRaters, AllFilters allF) {
		ArrayList<Integer> movieIds = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<Rating> ratings = new ArrayList<Rating>();
		for (int movie : movieIds) {
			double avgRating = getAverageByID(movie, minimalRaters);
			if (avgRating != 0.0) {
				if (allF.satisfies(movie)) {
					Rating rating = new Rating(movie, avgRating);
					ratings.add(rating);
				}
			}
		}

		return ratings;
	}

	private double dotProduct(Rater me, Rater r) {
		double sum = 0.0;
		ArrayList<Integer> meList = me.getItemsRated();
		ArrayList<Integer> rList = r.getItemsRated();
		for (int mItem : meList) {
			for (int rItem : rList) {
				if (mItem == rItem) {
					sum += (me.getRating(mItem) - 5.0) * (r.getRating(mItem) - 5.0);
				}
			}
		}
		return sum;

	}

	private ArrayList<Rating> getSimilarities(int id) {
		ArrayList<Rating> ratingSim = new ArrayList<Rating>();
		Rater me = RaterDatabase.getRater(id);
		for (Rater r : RaterDatabase.getRaters()) {
			int rId = r.getId();
			if (rId != id) {
				Rating rating = new Rating(r.getId(), dotProduct(me, r));
				if (rating.getValue() > 0.0) {
					ratingSim.add(rating);
				}
			}
		}
		Collections.sort(ratingSim, Collections.reverseOrder());
		return ratingSim;
	}

	public ArrayList<Rating> getSimilarRatings(int id, int numSimilarRaters, int minimalRaters,
			ArrayList<Integer> movieIds) {

		ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
		ArrayList<Rating> ratingSim = getSimilarities(id);
		if (ratingSim.size() == 0 || numSimilarRaters >= ratingSim.size()) {
			return ratingSim;
		}

		for (int movie : movieIds) {
			double weight = 0.0;
			int totalRatings = 0;
			for (int i = 0; i < numSimilarRaters; i++) {
				int raterSimID = ratingSim.get(i).getItem();
				Rater raterSIm = RaterDatabase.getRater(raterSimID);
				if (raterSIm.hasRating(movie)) {

					weight += ratingSim.get(i).getValue() * raterSIm.getRating(movie);
					totalRatings++;
				}
			}
			if (totalRatings >= minimalRaters) {
				double avgRatings = weight / totalRatings;
				avgRatingList.add(new Rating(movie, avgRatings));
			}
		}
		Collections.sort(avgRatingList, Collections.reverseOrder());
		return avgRatingList;
	}

	public ArrayList<Rating> getSimilarRatings2(int id, int numSimilarRaters, int minimalRaters,
			ArrayList<Integer> movieIds) {
		ArrayList<Rating> similarities = getSimilarities(id);
		ArrayList<Rating> avgWeightMovieRating = new ArrayList<Rating>();

		if (similarities.size() == 0 || numSimilarRaters >= similarities.size()) {
			return similarities;
		}

		for (int movie : movieIds) {
			double rate = 0.0;
			int n = 0;
			for (int k = 0; k < numSimilarRaters; k++) {
				Rating rater = similarities.get(k);
				int raterID = rater.getItem();
				double weight_rate = rater.getValue();
				double ory_rate = 0;
				try {
					ory_rate = RaterDatabase.getRater(raterID).getRating(movie);
				} catch (NullPointerException e) {
					continue;
				}
				rate += weight_rate * ory_rate;
				n++;
			}
			if (n >= minimalRaters) {
				avgWeightMovieRating.add(new Rating(movie, (rate / n)));
			}
		}
		Collections.sort(avgWeightMovieRating, Collections.reverseOrder());
		return avgWeightMovieRating;
	}

	public ArrayList<Rating> getSimilarRatingsByFilter(int id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
		ArrayList<Integer> movieIds = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
		ArrayList<Rating> ratingSim = getSimilarities(id);
		if (ratingSim.size() == 0 || numSimilarRaters >= ratingSim.size()) {

			return ratingSim;
		}
		for (Integer movie : movieIds) {
			double weight = 0.0;
			int totalRatings = 0;
			for (int i = 0; i < numSimilarRaters; i++) {
				int raterSimID = ratingSim.get(i).getItem();
				Rater raterSIm = RaterDatabase.getRater(raterSimID);
				if (raterSIm.hasRating(movie)) {

					weight += ratingSim.get(i).getValue() * raterSIm.getRating(movie);
					totalRatings++;
				}
			}
			if (totalRatings >= minimalRaters) {
				double avgRatings = weight / totalRatings;
				avgRatingList.add(new Rating(movie, avgRatings));
			}
		}
		Collections.sort(avgRatingList, Collections.reverseOrder());
		return avgRatingList;
	}
	public ArrayList<Rating> getSimilarRatingsByFilter2(int id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
		ArrayList<Integer> movieIds = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
		ArrayList<Rating> ratingSim = getSimilarities(id);
		if (ratingSim.size() == 0 || numSimilarRaters >= ratingSim.size()) {

			return ratingSim;
		}
		for (Integer movie : movieIds) {
			double weight = 0.0;
			int totalRatings = 0;
			for (int i = 0; i < numSimilarRaters; i++) {
				int raterSimID = ratingSim.get(i).getItem();
				Rater raterSIm = RaterDatabase.getRater(raterSimID);
				if (raterSIm.hasRating(movie)) {

					weight += ratingSim.get(i).getValue() * raterSIm.getRating(movie);
					totalRatings++;
				}
			}
			if (totalRatings >= minimalRaters) {
				double avgRatings = weight / totalRatings;
				avgRatingList.add(new Rating(movie, avgRatings));
			}
		}
		Collections.sort(avgRatingList, Collections.reverseOrder());
		return avgRatingList;
	}

	public ArrayList<Rating> getSimilarRatingsByFilter1(int id, int numSimilarRaters, int minimalRaters,
			Filter filterCriteria) {
		ArrayList<Integer> movieIds = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
		ArrayList<Rating> ratingSim = getSimilarities(id);
		if (ratingSim.size() == 0 || numSimilarRaters >= ratingSim.size()) {

			return ratingSim;
		}
		for (Integer movie : movieIds) {
			double weight = 0.0;
			int totalRatings = 0;
			for (int i = 0; i < numSimilarRaters; i++) {
				int raterSimID = ratingSim.get(i).getItem();
				Rater raterSIm = RaterDatabase.getRater(raterSimID);
				if (raterSIm.hasRating(movie)) {

					weight += ratingSim.get(i).getValue() * raterSIm.getRating(movie);
					totalRatings++;
				}
			}
			if (totalRatings >= minimalRaters) {
				double avgRatings = weight / totalRatings;
				avgRatingList.add(new Rating(movie, avgRatings));
			}
		}
		Collections.sort(avgRatingList, Collections.reverseOrder());
		return avgRatingList;
	}

	public ArrayList<Rating> getSimilarRatingsByFilter1(int id, int numSimilarRaters, int minimalRaters,
			AllFilters allF, ArrayList<Integer> movieIds) {
		ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
		ArrayList<Rating> ratingSim = getSimilarities(id);
		if (ratingSim.size() == 0 || numSimilarRaters >= ratingSim.size()) {

			return ratingSim;
		}
		for (Integer movie : movieIds) {
			double weight = 0.0;
			int totalRatings = 0;
			for (int i = 0; i < numSimilarRaters; i++) {
				int raterSimID = ratingSim.get(i).getItem();
				Rater raterSIm = RaterDatabase.getRater(raterSimID);
				if (raterSIm.hasRating(movie)) {

					weight += ratingSim.get(i).getValue() * raterSIm.getRating(movie);
					totalRatings++;
				}
			}
			if (totalRatings >= minimalRaters) {
				double avgRatings = weight / totalRatings;
				avgRatingList.add(new Rating(movie, avgRatings));
			}
		}
		Collections.sort(avgRatingList, Collections.reverseOrder());
		return avgRatingList;
	}
}
