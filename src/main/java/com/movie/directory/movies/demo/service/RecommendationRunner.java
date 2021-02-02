package com.movie.directory.movies.demo.service;
import java.util.ArrayList;

import com.movie.directory.movies.demo.businessLogic.GenreFilter;
import com.movie.directory.movies.demo.businessLogic.Rating;
import com.movie.directory.movies.demo.businessLogic.Recommender;
import com.movie.directory.movies.demo.businessLogic.TrueFilter;

public class RecommendationRunner implements Recommender{

	@Override
	public ArrayList<Integer> getItemsToRate() {
		ArrayList<Integer> movies =MovieDatabase.filterBy(new TrueFilter());
		ArrayList<Integer> moviesRandom= new ArrayList<Integer>();
		for(int i=0; i<20;i++) {
			moviesRandom.add(movies.get(i));
		}
		return  moviesRandom;
	}

	@Override
	public void printRecommendationsFor(int webRaterID) {
		FourthRatings fR = new FourthRatings();
        int numSimilarRaters = 20;
        int minimalRaters = 2;
        GenreFilter filterCriteria= new GenreFilter("Action");
		ArrayList<Rating> simRatings= fR.getSimilarRatingsByFilter1(webRaterID, numSimilarRaters, minimalRaters, filterCriteria);
		
		if(simRatings.size()==0) {
			System.out.println("<h3>no movies found based on your rating<h3>");
		}else {
			String header ="<table><th>movie title</th><th>Movie Rating</th>";
			String body = "";
			for(int i=0;i<simRatings.size();i++) {
				
			body+=("<tr><td>"+MovieDatabase.getTitle(simRatings.get(i).getItem()) +"</td><td>"+ +simRatings.get(i).getValue()+"</td></tr>");
			
			}
			System.out.println(header+body+"</table>");
		}
	}
	

}
