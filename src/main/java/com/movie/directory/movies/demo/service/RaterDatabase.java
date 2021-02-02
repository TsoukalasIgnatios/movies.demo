package com.movie.directory.movies.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.directory.movies.demo.businessLogic.Rating;
import com.movie.directory.movies.demo.dao.RaterRepository;
import com.movie.directory.movies.demo.entity.Rater;

@Service
public class RaterDatabase {
	private static HashMap<Integer, Rater> ourRaters;
	private static RaterRepository raterRepository;

	@Autowired
	public RaterDatabase(RaterRepository theRaterRepository) {
		raterRepository = theRaterRepository;

	}
	public RaterDatabase() {}

	public static void initialize() {
//		    // this method is only called from addRatings 
		if (ourRaters == null) {
			ourRaters = new HashMap<Integer, Rater>();
			addRatings();
		}
	}

	public static void addRatings() {
		initialize();
		List<Rater> raters = raterRepository.findAll();
//		for (Rater r : raters) {
//			
//			addRaterRating(r.getId(),r.getMovieId(), r.getRating());
//		}
		for (int i = 0; i < raters.size(); i++) {

			int id = raters.get(i).getId();
			int raterId = raters.get(i).getRaterId();

			int movieId = raters.get(i).getMovieId();

			double rating = raters.get(i).getRating();
			addRaterRating(raterId, movieId, rating);
		}

	}

	

	public static void addRaterRating(int raterID, int movieID, double rating) {
		 initialize();
		Rater rater = null;
		if (ourRaters.containsKey(raterID)) {
			rater = ourRaters.get(raterID);
		} else {
			rater = new Rater(raterID);
			
			ourRaters.put(raterID, rater);
		}
		rater.addRating(movieID, rating);
	}

	public static Rater getRater(int id) {
		initialize();

		return ourRaters.get(id);
	}

	public static ArrayList<Rater> getRaters() {
		initialize();
		ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());

		return list;
	}

	public static int size() {
		return ourRaters.size();
	}
}
