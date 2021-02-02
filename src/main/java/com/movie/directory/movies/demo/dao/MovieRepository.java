package com.movie.directory.movies.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.directory.movies.demo.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
