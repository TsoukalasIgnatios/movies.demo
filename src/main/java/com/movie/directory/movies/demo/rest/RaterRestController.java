package com.movie.directory.movies.demo.rest;

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

import com.movie.directory.movies.demo.entity.Rater;
import com.movie.directory.movies.demo.service.RaterService;

@RestController
@RequestMapping("/api")
public class RaterRestController {
	
//	private MovieService movieService;
	
	private RaterService raterService;
	
	@Autowired
	public RaterRestController(RaterService theRaterService) {
		raterService = theRaterService;
	}
	
	@GetMapping("/raters")
	public List<Rater> findAll(){
		return raterService.findAll();
	}
	@GetMapping("/raters/{raterId}")
	public Rater finById(@PathVariable int raterId) {
		Rater theRater = raterService.findById(raterId); 
		if(raterId==0) {
			throw new RuntimeException("This movie does not exist");
		}
		return theRater;
	}
	@PostMapping("/raters")
	public Rater saveRater(@RequestBody Rater theRater) {
		theRater.setId(0);
		raterService.save(theRater);
		return theRater;
		
	}
	@PutMapping("/raters")
	public Rater updateRater(@RequestBody Rater theRater) {
		raterService.save(theRater);
		return theRater;
		
	}
	@DeleteMapping("/raters/{raterId}")
	public String deleteRater(@PathVariable int raterId) {
		if(raterId==0) {
			throw new RuntimeException("This rater does not exist");
		}
		raterService.deleteRater(raterId);
		
		return "deleted rater with id :"+raterId;
	}
}
