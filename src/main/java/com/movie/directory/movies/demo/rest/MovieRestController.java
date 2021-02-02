package com.movie.directory.movies.demo.rest;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.directory.movies.demo.businessLogic.DirectorsFilter;
import com.movie.directory.movies.demo.businessLogic.GenreFilter;
import com.movie.directory.movies.demo.businessLogic.MinutesFilter;
import com.movie.directory.movies.demo.businessLogic.YearAfterFilter;
import com.movie.directory.movies.demo.entity.Movie;
import com.movie.directory.movies.demo.service.MovieService;

@RestController
@RequestMapping("/api")
public class MovieRestController {

	private MovieService movieService;

	@Autowired
	public MovieRestController(MovieService theMovieService) {
		movieService = theMovieService;
	}

	@GetMapping("/movies")
	public List<Movie> findAll() {
		return movieService.findAll();
	}

	@GetMapping("/movies/{movieId}")
	public Movie finById(@PathVariable int movieId) {
		Movie theMovie = movieService.findById(movieId);
		if (movieId == 0) {
			throw new RuntimeException("This movie does not exist");
		}
		return theMovie;
	}

	@PostMapping("/movies")
	public Movie saveMovie(@RequestBody Movie theMovie) {
		theMovie.setId(0);
		movieService.save(theMovie);
		return theMovie;

	}

	@PutMapping("/movies")
	public Movie updateMovie(@RequestBody Movie theMovie) {
		movieService.save(theMovie);
		return theMovie;

	}

	@DeleteMapping("/movies/{movieId}")
	public String deleteMovie(@PathVariable int movieId) {
		if (movieId == 0) {
			throw new RuntimeException("This movie does not exist");
		}
		movieService.deleteMovie(movieId);

		return "deleted movie with id :" + movieId;
	}

	@GetMapping("/movies/findAverageRatings/{minimalRaters}")
	public HashMap<String, Double> findAverageRatings(@PathVariable int minimalRaters) {

		return movieService.printAverageRatings(minimalRaters);
	}

	@GetMapping("/movies/printAverageRatingsByYearAfterAndGenre1/{Year},{Genre}")
	public HashMap<String, Double> printAverageRatingsByYearAfterAndGenre1(@PathVariable int Year,
			@PathVariable String Genre) {

		return movieService.printAverageRatingsByYearAfterAndGenre1(Year, Genre);
	}

	@GetMapping("/movies/movieRunnerSimilarRatings/{id},{numSimilarRaters},{minimalRaters}")
	public HashMap<String, Double> MovieRunnerSimilarRatings(@PathVariable int id, @PathVariable int numSimilarRaters,
			@PathVariable int minimalRaters) {

		return movieService.MovieRunnerSimilarRatings(id, numSimilarRaters, minimalRaters);
	}

	@GetMapping("/movies/printSimilarRatingsByGenre/{id},{numSimilarRaters},{minimalRaters},{geF}")
	public HashMap<String, Double> printSimilarRatingsByGenre(@PathVariable int id, @PathVariable int numSimilarRaters,
			@PathVariable int minimalRaters, @PathVariable GenreFilter geF) {

		return movieService.printSimilarRatingsByGenre(id, numSimilarRaters, minimalRaters, geF);
	}

	@GetMapping("/movies/printSimilarRatingsByDirector/{id},{numSimilarRaters},{minimalRaters}/{dirF}")
	public HashMap<String, Double> printSimilarRatingsByDirector(@PathVariable int id,
			@PathVariable int numSimilarRaters, @PathVariable int minimalRaters, @PathVariable DirectorsFilter dirF) {

		return movieService.printSimilarRatingsByDirector(id, numSimilarRaters, minimalRaters, dirF);
	}

	@GetMapping("/movies/printSimilarRatingsByYearAfterAndMinutes/{id},{numSimilarRaters},{minimalRaters},{year},{minMax},{minMin}")
	HashMap<String, Double> printSimilarRatingsByYearAfterAndMinutes(@PathVariable int id,
			@PathVariable int numSimilarRaters, @PathVariable int minimalRaters, @PathVariable int year,
			@PathVariable int minMax, @PathVariable int minMin) {

		return movieService.printSimilarRatingsByYearAfterAndMinutes(id, numSimilarRaters, minimalRaters, year, minMax,
				minMin);
	}
}
