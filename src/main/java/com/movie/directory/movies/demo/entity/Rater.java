package com.movie.directory.movies.demo.entity;

import java.util.ArrayList;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movie.directory.movies.demo.businessLogic.Rating;

@Entity
@Table(name = "ratings_table")
public class Rater {
	@Column(name = "rater_id")
	private int raterId;
	@Column(name = "movie_id")
	private int movieId;
	@Column(name = "rating")
	private double rating;
	@Column(name = "time")
	private String time;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Transient
//	@JsonIgnore
	public HashMap<Integer, Rating> myRatings;

	public Rater() {
	}

	public Rater(int id) {
		this.id = id;
		myRatings = new HashMap<Integer, Rating>();
	}

	public Rater(int movieId, double rating) {
		this.movieId = movieId;
		this.rating = rating;
	}

	public int getRaterId() {
		return raterId;
	}

	public void setRaterId(int raterId) {
		this.raterId = raterId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Rater [id=" + id + ", movieId=" + movieId + ", rating=" + rating + ", time=" + time + "]";
	}

	public void addRating(int item, double rating) {

		myRatings.put(item, new Rating(item, rating));
	}

	public boolean hasRating(int movieID) {
		if (myRatings.containsKey(movieID)) {
			return true;
		}

		return false;
	}

	public double getRating(int item) {
		for (int s : myRatings.keySet()) {
			if (s == item) {
				return myRatings.get(s).getValue();
			}
		}

		return -1;
	}

	public int numRatings() {
		return myRatings.size();
	}

	@JsonIgnore
	public ArrayList<Integer> getItemsRated() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int item : myRatings.keySet()) {
			list.add(item);
		}

		return list;
	}

}
