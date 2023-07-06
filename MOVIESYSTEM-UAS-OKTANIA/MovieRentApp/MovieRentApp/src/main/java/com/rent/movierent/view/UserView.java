package com.rent.movierent.view;

import com.rent.movierent.model.User;

import java.util.List;

public class UserView {
    public void printUserDetails(String userId, String password){
        System.out.println("User:");
        System.out.println("User ID: " + userId);
        System.out.println("Password: " + password);
    }

    public void displayUserTable(List<User> users) {
        System.out.println("User List:");
        System.out.println("-----------------------------");
        System.out.printf("%-10s %-15s%n", "User ID", "Password");
        System.out.println("-----------------------------");
        for (User user : users) {
            System.out.printf("%-10s %-15s%n", user.getUserId(), user.getPassword());
        }
        System.out.println("-----------------------------");
    }

}
