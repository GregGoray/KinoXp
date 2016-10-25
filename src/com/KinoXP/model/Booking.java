package com.KinoXP.model;

/**
 * Created by Girts Zemitis on 01/03/2016.
 * https://github.com/GirtsZemitis
 */
public class Booking {

    private String date;



    private String time;
    private String title;
    private int seats;
    private String phoneNumber;
    private boolean paid;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phone_number) {
        this.phoneNumber = phone_number;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Booking(String date, String time, String title, int seats, String phoneNumber,boolean paid) {

        this.date = date;
        this.time = time;
        this.title = title;
        this.seats = seats;
        this.phoneNumber = phoneNumber;
        this.paid = paid;
    }
}
