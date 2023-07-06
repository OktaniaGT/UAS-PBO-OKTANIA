package com.rent.movierent;

import com.rent.movierent.controller.MovieController;
import com.rent.movierent.controller.RentController;
import com.rent.movierent.controller.UserController;
import com.rent.movierent.model.User;
import com.rent.movierent.repository.MovieRepository;
import com.rent.movierent.repository.RentRepository;
import com.rent.movierent.repository.UserRepository;
import com.rent.movierent.view.MovieView;
import com.rent.movierent.view.RentView;
import com.rent.movierent.view.UserView;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException, ParseException {
        Scanner scanner = new Scanner(System.in);

        UserRepository userRepository = new UserRepository();
        UserView userView = new UserView();
        UserController userController = new UserController(userRepository, userView);

        MovieRepository movieRepository = new MovieRepository();
        MovieView movieView = new MovieView();
        MovieController movieController = new MovieController(movieRepository, movieView);

        RentRepository rentRepository = new RentRepository();
        RentView rentView = new RentView();
        RentController rentController = new RentController(rentRepository, rentView);

        System.out.println("Enter Admin ID: ");
        String adminId = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        User admin = userRepository.getUser(adminId);
        if(admin != null && admin.getPassword().equals(password)) {
            System.out.println("Admin Login Success!");

            while(true) {
                System.out.println("1. User");
                System.out.println("2. Movie");
                System.out.println("3. Rent");
                System.out.println("4. Exit");
                System.out.println("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                if(option == 1) {
                    System.out.println("1. Add User");
                    System.out.println("2. Change Password");
                    System.out.println("Choose an option: ");
                    int userOption = scanner.nextInt();
                    scanner.nextLine();
                    if(userOption == 1) {
                        userController.addUser(scanner);
                    } else if(userOption == 2) {
                        userController.showAllUsers();
                        userController.changePassword(scanner);
                    } else {
                        System.out.println("Invalid option");
                    }
                } else if(option == 2) {
                    System.out.println("1. Add Movie");
                    System.out.println("2. Delete Movie");
                    System.out.println("Choose an option: ");
                    int movieOption = scanner.nextInt();
                    scanner.nextLine();
                    if(movieOption == 1) {
                        movieController.addMovie(scanner);
                    } else if(movieOption == 2) {
                        movieController.showAllMovies();
                        movieController.deleteMovie(scanner);
                    } else {
                        System.out.println("Invalid option");
                    }
                } else if(option == 3) {
                    System.out.println("1. Add Rent");
                    System.out.println("2. Return Rent");
                    System.out.println("Choose an option: ");
                    int rentOption = scanner.nextInt();
                    scanner.nextLine();
                    if(rentOption == 1) {
                        movieController.showAllMovies();
                        rentController.addRent(scanner, userRepository);
                    } else if(rentOption == 2) {
                        rentController.showAllRents();
                        rentController.returnRent(scanner);
                    } else {
                        System.out.println("Invalid option");
                    }
                } else if(option == 4) {
                    System.out.println("Exiting...");
                    break;
                } else {
                    System.out.println("Invalid option");
                }
            }
        } else {
            System.out.println("Invalid Admin ID or Password");
        }

        scanner.close();
    }
}