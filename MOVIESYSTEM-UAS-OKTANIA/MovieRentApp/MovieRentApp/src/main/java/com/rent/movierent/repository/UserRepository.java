package com.rent.movierent.repository;

import com.rent.movierent.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private final String url = "jdbc:mysql://localhost:3306/moviesystem";
    private final String username = "root";
    private final String password = "";

    public void addUser(User user) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (userId, password) VALUES (?, ?)");
        stmt.setString(1, user.getUserId());
        stmt.setString(2, user.getPassword());
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public User getUser(String userId) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE userId = ?");
        stmt.setString(1, userId);
        ResultSet rs = stmt.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User(rs.getString("userId"), rs.getString("password"));
        }
        rs.close();
        stmt.close();
        connection.close();
        return user;
    }

    public void changePassword(String userId, String newPassword) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET password = ? WHERE userId = ?");
        stmt.setString(1, newPassword);
        stmt.setString(2, userId);
        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
        ResultSet rs = stmt.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User(rs.getString("userId"), rs.getString("password"));
            users.add(user);
        }
        rs.close();
        stmt.close();
        connection.close();
        return users;
    }
}
