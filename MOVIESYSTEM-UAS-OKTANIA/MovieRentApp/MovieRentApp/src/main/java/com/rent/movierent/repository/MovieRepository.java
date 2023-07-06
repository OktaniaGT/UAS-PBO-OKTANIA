package com.rent.movierent.repository;

import com.rent.movierent.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    String url = "jdbc:mysql://localhost:3306/moviesystem";
    String user = "root";
    String password = "";

    public void addMovie(Movie movie) {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {
            String query = "INSERT INTO Movies VALUES ('"
                    + movie.getId() + "', '"
                    + movie.getMovieTitle() + "', '"
                    + movie.getMovieGenre() + "')";
            st.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {
            String query = "SELECT * FROM Movies";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("movieTitle");
                String genre = rs.getString("movieGenre");
                Movie movie = new Movie(id, title, genre);
                movies.add(movie);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    public void deleteMovie(String movieId) throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);

        PreparedStatement checkRentStmt = connection.prepareStatement("SELECT * FROM rents WHERE movieId = ?");
        checkRentStmt.setString(1, movieId);
        ResultSet rentResult = checkRentStmt.executeQuery();
        if (rentResult.next()) {
            System.out.println("Cannot delete the movie. There are associated rents.");
            return;
        }

        PreparedStatement stmt = connection.prepareStatement("DELETE FROM movies WHERE id = ?");
        stmt.setString(1, movieId);
        stmt.executeUpdate();

        stmt.close();
        checkRentStmt.close();
        connection.close();

        System.out.println("Movie ID: " + movieId + " has been deleted.");
    }
}
