package com.g4net.tmdbapp.model;

public class MovieNotFoundException extends RuntimeException
{
    public MovieNotFoundException()
    {
        super("Movie not found!");
    }
}
