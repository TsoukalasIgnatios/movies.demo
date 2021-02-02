package com.movie.directory.movies.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.directory.movies.demo.dao.RaterRepository;
import com.movie.directory.movies.demo.entity.Rater;

@Service
public class RaterServiceImpl implements RaterService {

	private RaterRepository raterRepository;
	@Autowired
	public RaterServiceImpl (RaterRepository theRaterDAO) {
		
		raterRepository = theRaterDAO;
	}
	
	@Override
	public List<Rater> findAll() {
		
		return raterRepository.findAll();
	}

	@Override
	public Rater findById(int theId) {
		Optional<Rater> result = raterRepository.findById(theId);
		
		Rater theRater =null;
		if(result.isPresent()) {
			theRater = result.get();
		}else {
			throw new RuntimeException("Did not find movie id - "+ theId);
		}
		return theRater;
	}

	@Override
	public void save(Rater theRater) {
		raterRepository.save(theRater);
	}

	@Override
	public void deleteRater(int theId) {
		raterRepository.deleteById(theId);
	}
	/*
	@Override
	public void printAverageRatings(int minimalRaters) {
		FourthRatings fR = new FourthRatings();
		ArrayList<Rating> list = fR.getAverageRatings(minimalRaters);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size() - i - 1; j++) {
				if (list.get(j).compareTo(list.get(j + 1)) > 0) {
					Collections.swap(list, j, j + 1);
				}
			}
		}
		for (Rating rating : list) {
			System.out.println(MovieDatabase.getTitle(rating.getItem()) + " " + rating.getValue());
		}
	}

	@Override
	public void printAverageRatingsByYearAfterAndGenre() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MovieRunnerSimilarRatings() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printSimilarRatingsByGenre() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printSimilarRatingsByDirector() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printSimilarRatingsByGenreAndMinutes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printSimilarRatingsByYearAfterAndMinutes() {
		// TODO Auto-generated method stub
		
	}
*/
}

