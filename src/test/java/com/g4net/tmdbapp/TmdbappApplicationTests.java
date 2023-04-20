package com.g4net.tmdbapp;

import com.g4net.tmdbapp.model.Movie;
import com.g4net.tmdbapp.service.MovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@Import(TestSecurityConfig.class)
@ActiveProfiles("test")
public class TmdbappApplicationTests {

    @Autowired
    private MovieService movieService;

    @Test
    public void testFetchMovieList() {
        List<Movie> movieList = movieService.fetchMovieList();
        Assertions.assertNotNull(movieList);
        Assertions.assertFalse(movieList.isEmpty());
    }

    @Test
    public void testFetchMovieById() {
        Long movieId = 20L;
        Movie movie = movieService.fetchMovieById(movieId);
        Assertions.assertEquals(movieId, movie.getId());

        movieId = 1L;
        movie = movieService.fetchMovieById(movieId);
        Assertions.assertNull(movie);
    }

    @Test
    public void testFetchMoviesByQuery() {
        String query = "The Godfather";
        List<Movie> movies = movieService.fetchMoviesByQuery(query);
        Assertions.assertFalse(movies.isEmpty());
        for (Movie movie : movies) {
            Assertions.assertTrue(isTitleContained(query, movie.getTitle()));
        }
    }

    public static boolean isTitleContained(String title, String otherTitle) {
        String[] titleWords = title.split("\\s+");

        for (String titleWord : titleWords)
            if (!otherTitle.contains(titleWord)) return false;
        return true;
    }
}
