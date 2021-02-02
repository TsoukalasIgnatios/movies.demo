package com.movie.directory.movies.demo.businessLogic;

import com.movie.directory.movies.demo.service.MovieDatabase;

public class GenreFilter implements Filter{
	private String myGenre;
	public GenreFilter(String genre) {
		this.myGenre = genre;
	}
	
	
	@Override
	public boolean satisfies(int id) {
		
		return MovieDatabase.getGenres(id).contains(myGenre);
		
	}
}
