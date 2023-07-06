package com.rent.movierent.view;

import com.rent.movierent.model.Rent;

import java.util.Date;
import java.util.List;

public class RentView {
    public void printRentDetails(String id, String rentDate, String returnDate, String movieId, String renter) {
        System.out.println("Rent ID: " + id);
        System.out.println("Rent Date: " + rentDate);
        System.out.println("Return Date: " + returnDate);
        System.out.println("Movie ID: " + movieId);
        System.out.println("Renter ID: " + renter);
    }

    public void displayRentTable(List<Rent> rents) {
        System.out.println("Rent List:");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-10s %-12s %-12s %-10s %-10s%n", "Rent ID", "Rent Date", "Return Date", "Movie ID", "Renter ID");
        System.out.println("-----------------------------------------------------");
        for (Rent rent : rents) {
            System.out.printf("%-10s %-12s %-12s %-10s %-10s%n", rent.getId(), rent.getRentDate(), rent.getReturnDate(), rent.getMovieId(), rent.getRenter().getUserId());
        }
        System.out.println("-----------------------------------------------------");
    }
}