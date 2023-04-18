package com.g4net.tmdbapp.controller;

import com.g4net.tmdbapp.model.Movie;
import com.g4net.tmdbapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.fetchMovieById(id);
        if (movie == null) {
            String errorMessage = "Movie with id " + id + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/search")
    public List<Movie> getMovieSearchResult(@RequestParam("query") String query){
        return movieService.fetchMoviesByQuery(query);
    }
}