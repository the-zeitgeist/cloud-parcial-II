package com.cloud.movie.services;

import com.cloud.movie.entities.Movie;

import java.util.List;

public interface MovieService {
    void save(Movie movie);
    void delete(Movie movie);
    List<Movie> findAll();
    Movie findById(Long id);
}
