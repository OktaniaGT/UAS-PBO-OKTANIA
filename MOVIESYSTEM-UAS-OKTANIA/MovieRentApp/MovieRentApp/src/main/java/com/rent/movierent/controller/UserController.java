package com.rent.movierent.controller;

import com.rent.movierent.model.User;
import com.rent.movierent.repository.UserRepository;
import com.rent.movierent.view.UserView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserController {
    private final UserRepository userRepository;
    private final UserView userView;

    public UserController(UserRepository userRepository, UserView userView) {
        this.userRepository = userRepository;
        this.userView = userView;
    }

    public void addUser(Scanner scanner) throws SQLException {
        System.out.println("Enter new user ID: ");
        String userId = scanner.nextLine();
        System.out.println("Enter new user password: ");
        String password = scanner.nextLine();
        User user = new User(userId, password);
        userRepository.addUser(user);
        userView.printUserDetails(user.getUserId(), user.getPassword());
    }

    public void changePassword(Scanner scanner) throws SQLException {
        System.out.println("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.println("Enter new password: ");
        String password = scanner.nextLine();
        userRepository.changePassword(userId, password);
        System.out.println("Password changed successfully for user: " + userId);
    }

    public void showAllUsers() throws SQLException {
        List<User> users = userRepository.getAllUsers();
        userView.displayUserTable(users);
    }
}