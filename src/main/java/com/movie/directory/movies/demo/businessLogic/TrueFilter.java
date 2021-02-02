package com.movie.directory.movies.demo.businessLogic;

public class TrueFilter implements Filter{
	@Override
	public boolean satisfies(int id) {
		return true;
	}
}
