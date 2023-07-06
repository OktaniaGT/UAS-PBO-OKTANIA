package com.rent.movierent.model;

public class Movie {
    private String id;
    private String movieTitle;
    private String movieGenre;

    public Movie(String id, String movieTitle, String movieGenre) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
    }

    public String getId() {
        return id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

}
