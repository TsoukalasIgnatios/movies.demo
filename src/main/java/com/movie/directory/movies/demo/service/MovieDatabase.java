package com.movie.directory.movies.demo.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.directory.movies.demo.businessLogic.Filter;
import com.movie.directory.movies.demo.dao.MovieRepository;
import com.movie.directory.movies.demo.entity.Movie;
@Service
public class MovieDatabase {
	private static MovieRepository movieRepository;
	@Autowired
	public MovieDatabase (MovieRepository theMovieRepository) {
		movieRepository = theMovieRepository;
	}
	private static HashMap<Integer, Movie> ourMovies;


    public static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap<Integer,Movie>();
            loadMovies();
        }
    }	

	
    public static void loadMovies() {
    	 List<Movie> movieslist = movieRepository.findAll();
	        for (Movie m : movieslist) {
	            ourMovies.put(m.getId(), m);
	        }
    }

    public static boolean containsID(int id) {
        initialize();
        return ourMovies.containsKey(id);
    }

    public static int getYear(int id) {
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(int id) {
        initialize();
        return ourMovies.get(id).getGenre();
    }

    public static String getTitle(int id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(int id) {
        initialize();
        return ourMovies.get(id);
    }

    public static String getPoster(int id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(int id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(int id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(int id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }

    public static int size() {
        return ourMovies.size();
    }

    public static ArrayList<Integer> filterBy(Filter filterCriteria) {
    	initialize();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int id : ourMovies.keySet()) {
            if (filterCriteria.satisfies(id)) {
                list.add(id);
            }
        }
        
        return list;
    }
}
