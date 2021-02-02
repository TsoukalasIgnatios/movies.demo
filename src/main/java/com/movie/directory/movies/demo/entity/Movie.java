package com.movie.directory.movies.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="movies_table")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="year")
	private int year;
	@Column(name="country")
	private String country;
	@Column(name="genre")
	private String genre;
	@Column(name="director")
	private String director;
	@Column(name="minutes")
	private int minutes;
	@Column(name="poster")
	private String poster;
	
	public Movie () {}

	   

	    public Movie (int anID, String aTitle, int aYear, String theGenres) {
	        // just in case data file contains extra whitespace
	        id = anID;
	        title = aTitle.trim();
	        year = aYear;
	        genre = theGenres;
	    }

	    public Movie (int anID, String aTitle, int aYear, String theGenres, String aDirector,
	    String aCountry, String aPoster, int theMinutes) {
	        // just in case data file contains extra whitespace
	        id = anID;
	        title = aTitle.trim();
	        year = aYear;
	        genre = theGenres;
	        director = aDirector;
	        country = aCountry;
	        poster = aPoster;
	        minutes = theMinutes;
	    }

	

	public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public String getDirector() {
			return director;
		}

		public void setDirector(String director) {
			this.director = director;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getPoster() {
			return poster;
		}

		public void setPoster(String poster) {
			this.poster = poster;
		}

		public int getMinutes() {
			return minutes;
		}

		public void setMinutes(int minutes) {
			this.minutes = minutes;
		}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", genre=" + genre + ", year=" + year + ", director=" + director
				+ ", country=" + country + ", poster=" + poster + ", minutes=" + minutes + "]";
	}

	
	
	
}
