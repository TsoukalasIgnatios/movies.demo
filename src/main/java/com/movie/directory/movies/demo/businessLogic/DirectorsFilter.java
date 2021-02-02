package com.movie.directory.movies.demo.businessLogic;

import com.movie.directory.movies.demo.service.MovieDatabase;

public class DirectorsFilter implements Filter{
	private String[] myDir;

	public DirectorsFilter(String directors) {
		this.myDir = directors.split(",");
	}

	@Override
	public boolean satisfies(int id) {
		for (int i = 0; i < myDir.length; i++) {
			if (MovieDatabase.getDirector(id).contains(myDir[i])) {
				return true;
			}
		}
		return false;
	}
}
