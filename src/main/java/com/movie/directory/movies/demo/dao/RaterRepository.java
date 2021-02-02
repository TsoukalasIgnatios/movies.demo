package com.movie.directory.movies.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.directory.movies.demo.entity.Rater;

public interface RaterRepository extends JpaRepository<Rater, Integer> {

}
