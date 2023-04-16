package com.g4net.tmdbapp.service;

import com.g4net.tmdbapp.model.Movie;
import com.g4net.tmdbapp.model.MovieList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {
    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    @Value("${tmdb.base.url}")
    private String tmdbBaseUrl;

    @Value("${tmdb.top.rated.movie.url}")
    private String topRatedMovieUrl;

    @Value("${tmdb.search.movie.url}")
    private String tmdbSearchMovieUrl;

    @Value("${tmdb.movie.id.url}")
    private String tmdbMovieIdUrl;

    @Autowired
    private RestTemplate restTemplate;

    public URI getURI(String patternUrl, String [] exp) {
        UriComponents uriComponents =
                UriComponentsBuilder.fromUriString(patternUrl).build()
                        .expand((Object[]) exp)
                        .encode();

        return uriComponents.toUri();
    }

    public List<Movie> fetchMovieList() {
        URI uri = getURI(topRatedMovieUrl, new String[] {tmdbBaseUrl, tmdbApiKey});
        MovieList movieList = restTemplate.getForObject(uri, MovieList.class);
        return movieList.getResults();
    }

    public Movie fetchMovieById(Long id) {

        URI uri = getURI(tmdbMovieIdUrl, new String [] {tmdbBaseUrl, id.toString(), tmdbApiKey});
        return restTemplate.getForObject(uri, Movie.class);
    }

    public List<Movie> fetchMoviesBySearchQuery(String query) {

        URI uri = getURI(tmdbSearchMovieUrl, new String [] {tmdbBaseUrl, tmdbApiKey, query});
        MovieList movieResults = restTemplate.getForObject(uri, MovieList.class);
        return movieResults.getResults();
    }
}
