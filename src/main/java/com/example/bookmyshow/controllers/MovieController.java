package com.example.bookmyshow.controllers;


import com.example.bookmyshow.models.Movie;
import com.example.bookmyshow.services.MovieService;
import com.example.bookmyshow.services.MovieServiceImplementation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")

public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable long id) {
        return movieService.findMovieById(id).orElse(null);
    }

}
