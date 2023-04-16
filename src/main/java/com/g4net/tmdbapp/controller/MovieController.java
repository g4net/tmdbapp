package com.g4net.tmdbapp.controller;

import com.g4net.tmdbapp.model.Movie;
import com.g4net.tmdbapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tmdbapp")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getTopMovies(){
        return movieService.fetchMovieList();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable("id") Long id){
        return movieService.fetchMovieById(id);
    }

    @GetMapping("/search")
    public List<Movie> getMovieSearchResult(@RequestParam("query") String query){
        return movieService.fetchMoviesBySearchQuery(query);
    }
}