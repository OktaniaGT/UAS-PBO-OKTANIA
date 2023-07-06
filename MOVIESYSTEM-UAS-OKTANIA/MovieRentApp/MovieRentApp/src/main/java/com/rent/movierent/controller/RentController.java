package com.rent.movierent.controller;

import com.rent.movierent.model.Rent;
import com.rent.movierent.model.User;
import com.rent.movierent.repository.RentRepository;
import com.rent.movierent.repository.UserRepository;
import com.rent.movierent.view.RentView;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RentController {
    private final RentRepository rentRepository;
    private final RentView rentView;

    public RentController(RentRepository rentRepository, RentView rentView) {
        this.rentRepository = rentRepository;
        this.rentView = rentView;
    }

    public void addRent(Scanner scanner, UserRepository userRepository) throws SQLException, ParseException {
        System.out.println("Enter rent ID: ");
        String rentId = scanner.nextLine();
        System.out.println("Enter movie ID to rent: ");
        String rentMovieId = scanner.nextLine();
        System.out.println("Enter rent date (YYYY-MM-DD): ");
        String rentDate = scanner.nextLine();
        System.out.println("Enter return date (YYYY-MM-DD): ");
        String returnDate = scanner.nextLine();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date formattedRentDate = formatter.parse(rentDate);
        Date formattedReturnDate = formatter.parse(returnDate);
        System.out.println("Enter renter ID: ");
        String renterId = scanner.nextLine();
        User renter = userRepository.getUser(renterId);

        if (renter != null) {
            Rent rent = new Rent(rentId, formattedRentDate, formattedReturnDate, rentMovieId, renter);
            rentRepository.addRent(rent);
            rentView.printRentDetails(rent.getId(), rent.getRentDate().toString(), rent.getReturnDate().toString(), rent.getMovieId(), rent.getRenter().getUserId());
        } else {
            System.out.println("Invalid renter ID");
        }
    }

    public void returnRent(Scanner scanner) throws SQLException {
        System.out.println("Enter rent ID to return: ");
        String rentId = scanner.nextLine();
        rentRepository.returnRent(rentId);
        System.out.println("Rent ID: " + rentId + " has been returned.");
    }

    public void showAllRents() throws SQLException {
        List<Rent> rents = rentRepository.getAllRents();
        rentView.displayRentTable(rents);
    }
}