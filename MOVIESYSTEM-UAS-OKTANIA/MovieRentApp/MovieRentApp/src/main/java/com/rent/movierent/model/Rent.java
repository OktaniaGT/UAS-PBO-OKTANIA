package com.rent.movierent.model;

import java.util.Date;

public class Rent {
    private String id;
    private Date rentDate;
    private Date returnDate;
    private String movieId;
    private User renter;

    public Rent(String id, Date rentDate, Date returnDate, String movieId, User renter) {
        this.id = id;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.movieId = movieId;
        this.renter = renter;
    }

    public String getId() {
        return id;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getMovieId() {
        return movieId;
    }

    public User getRenter() {
        return renter;
    }

}
