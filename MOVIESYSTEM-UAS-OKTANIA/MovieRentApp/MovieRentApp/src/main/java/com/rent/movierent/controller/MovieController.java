package com.rent.movierent.controller;

import com.rent.movierent.model.Movie;
import com.rent.movierent.repository.MovieRepository;
import com.rent.movierent.view.MovieView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MovieController {
    private final MovieRepository movieRepository;
    private final MovieView movieView;

    public MovieController(MovieRepository movieRepository, MovieView movieView) {
        this.movieRepository = movieRepository;
        this.movieView = movieView;
    }

    public void addMovie(Scanner scanner) throws SQLException {
        System.out.println("Enter movie ID: ");
        String movieId = scanner.nextLine();
        System.out.println("Enter movie title: ");
        String title = scanner.nextLine();
        System.out.println("Enter movie genre: ");
        String genre = scanner.nextLine();
        Movie movie = new Movie(movieId, title, genre);
        movieRepository.addMovie(movie);
        movieView.printMovieDetails(movie.getId(), movie.getMovieTitle(), movie.getMovieGenre());
    }

    public void deleteMovie(Scanner scanner) throws SQLException {
        System.out.println("Enter movie ID to delete: ");
        String movieId = scanner.nextLine();
        movieRepository.deleteMovie(movieId);
    }

    public void showAllMovies() throws SQLException {
        List<Movie> movies = movieRepository.getAllMovies();
        movieView.displayMovieTable(movies);
    }
}
