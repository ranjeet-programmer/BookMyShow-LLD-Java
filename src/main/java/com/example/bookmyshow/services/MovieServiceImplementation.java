package com.example.bookmyshow.services;


import com.example.bookmyshow.models.Movie;
import com.example.bookmyshow.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImplementation implements MovieService{


    private final MovieRepository movieRepository;

    public MovieServiceImplementation(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAllMovies() {
        return  this.movieRepository.findAll();
    }

    public Optional<Movie> findMovieById(long id) {
        return this.movieRepository.findById(id);
    }
}
