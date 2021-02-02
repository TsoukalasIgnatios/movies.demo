package com.movie.directory.movies.demo.businessLogic;

import com.movie.directory.movies.demo.service.MovieDatabase;

public class MinutesFilter implements Filter{
//	private int myTime;
	private int myMax;
	private int myMin;

	public MinutesFilter( int max, int min) {
		//this.myTime = id;
		this.myMax = max;
		this.myMin = min;
	}

	@Override
	public boolean satisfies(int id) {

		if (MovieDatabase.getMinutes(id)<=myMax&&MovieDatabase.getMinutes(id)>=myMin) {
			return true;
		}

		return false;
	}
}
