package com.rent.movierent.view;

import com.rent.movierent.model.Movie;

import java.util.List;

public class MovieView {

    public void printMovieDetails(String movieId, String title, String genre){
        System.out.println("Movie:");
        System.out.println("ID: " + movieId);
        System.out.println("Title: " + title);
        System.out.println("Genre: " + genre);
    }

    public void displayMovieTable(List<Movie> movies) {
        System.out.println("Movie List:");
        System.out.println("-------------------------------------");
        System.out.printf("%-10s %-20s %-15s%n", "Movie ID", "Title", "Genre");
        System.out.println("-------------------------------------");
        for (Movie movie : movies) {
            System.out.printf("%-10s %-20s %-15s%n", movie.getId(), movie.getMovieTitle(), movie.getMovieGenre());
        }
        System.out.println("-------------------------------------");
    }



}