package com.rent.movierent.repository;

import com.rent.movierent.model.Rent;
import com.rent.movierent.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentRepository {

    private final String url = "jdbc:mysql://localhost:3306/moviesystem";
    private final String username = "root";
    private final String password = "";

    public void addRent(Rent rent) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO rents (id, rentDate, returnDate, movieId, renter) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, rent.getId());
        stmt.setDate(2, new java.sql.Date(rent.getRentDate().getTime()));
        stmt.setDate(3, new java.sql.Date(rent.getReturnDate().getTime()));
        stmt.setString(4, rent.getMovieId());
        stmt.setString(5, rent.getRenter().getUserId());
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public void returnRent(String id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM rents WHERE id = ?");
        stmt.setString(1, id);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public List<Rent> getAllRents() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM rents");
        ResultSet rs = stmt.executeQuery();
        List<Rent> rents = new ArrayList<>();
        while (rs.next()) {
            User renter = new User(rs.getString("renter"), "");
            Date rentDate = new Date(rs.getDate("rentDate").getTime());
            Date returnDate = new Date(rs.getDate("returnDate").getTime());
            Rent rent = new Rent(rs.getString("id"), rentDate, returnDate, rs.getString("movieId"), renter);
            rents.add(rent);
        }
        rs.close();
        stmt.close();
        connection.close();
        return rents;
    }
}
