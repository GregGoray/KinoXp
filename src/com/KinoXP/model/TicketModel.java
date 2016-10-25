package com.KinoXP.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Lucia/Paula on 2/24/16.
 */
public class TicketModel {

    private static Connection conn = LoginViewModel.conn;

    //GET MOVIES FROM DB
    public ObservableList<String> getMovies() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        Booking booking;
        String out = "";


        try {
            String sql = "SELECT title FROM Movie";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                String movie = (resultSet.getString(1));


                observableList.add(movie);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return observableList;
    }

    public ObservableList<Booking> getBookingByPhoneNumber(String phoneNumber) {
        ObservableList<Booking> observableList = FXCollections.observableArrayList();
        Booking booking;
        String out = "";


        try {
            String sql = "SELECT * FROM Booking1 WHERE bookingId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, phoneNumber);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                booking = new Booking(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5), resultSet.getBoolean(6));


                observableList.add(booking);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return observableList;
    }

    //GET BOOKINGS BY TITLE
    public ObservableList<Booking> getBookingByTitle(String title) {
        ObservableList<Booking> observableList = FXCollections.observableArrayList();
        Booking booking;
        String out = "";


        try {
            String sql = "SELECT * FROM Booking1 WHERE title=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, title);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                booking = new Booking(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5), resultSet.getBoolean(6));


                observableList.add(booking);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return observableList;
    }

    //GET BOOKINGS BY TITLE, DATE AND TIME
    public ObservableList<Booking> getMovieByDateTime(String date, String time, String title) {
        ObservableList<Booking> observableList = FXCollections.observableArrayList();
        Booking booking;
        String out = "";


        try {
            String sql = "SELECT * FROM Booking1 WHERE date=? AND time=? AND title=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //noinspection JpaQueryApiInspection
            preparedStatement.setString(1, date);
            //noinspection JpaQueryApiInspection
            preparedStatement.setString(2, time);
            //noinspection JpaQueryApiInspection
            preparedStatement.setString(3, title);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                booking = new Booking(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5), resultSet.getBoolean(6));


                observableList.add(booking);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return observableList;
    }

    //GET BOOKINGS BY PAID TICKET
    public ObservableList<Booking> getBookingByPaidTicket(String checkBox) {
        ObservableList<Booking> observableList = FXCollections.observableArrayList();
        Booking booking;
        String out = "";


        try {
            String sql = "SELECT * FROM Booking1 WHERE isPaid=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, checkBox);


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                booking = new Booking(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5), resultSet.getBoolean(6));


                observableList.add(booking);

            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return observableList;
    }

    //GET BOOKINGS BY RESERVED TICKET
    public ObservableList<Booking> getBookingByUnpaidTicket(String checkBox2) {
        ObservableList<Booking> observableList = FXCollections.observableArrayList();
        Booking booking;

        try {
            String sql = "SELECT * FROM Booking1 WHERE isPaid=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, checkBox2);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                booking = new Booking(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5), resultSet.getBoolean(6));

                observableList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    //GET BOOKINGS BY THEATER
    public ObservableList<Booking> getBookingByTheater(String theatre) {
        ObservableList<Booking> observableList = FXCollections.observableArrayList();
        Booking booking;

        try {
            String sql = "SELECT date, time, Booking1.title, seats, bookingId, isPaid FROM Booking1 JOIN Movie ON Booking1.title = Movie.title WHERE cinemaRoomName=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //noinspection JpaQueryApiInspection
            preparedStatement.setString(1, theatre);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                booking = new Booking(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getString(5), resultSet.getBoolean(6));

                observableList.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return observableList;
    }
}

