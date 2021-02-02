package com.movie.directory.movies.demo.businessLogic;

import com.movie.directory.movies.demo.service.MovieDatabase;

public class YearAfterFilter implements Filter{
	
private int myYear;
	
	public YearAfterFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(int id) {
		return MovieDatabase.getYear(id) >= myYear;
	}
}
