package com.KinoXP.model;

import com.KinoXP.controller.AddBookingViewController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Girts Zemitis on 01/03/2016.
 * https://github.com/GirtsZemitis
 */
public class AddBookingViewModel {

    Connection conn = LoginViewModel.conn;

    public Booking insertBooking(String date, String time, String title, int seats, String phoneNumber,boolean paid) {
        try {
            String sql = "INSERT INTO Booking1 (date, time, title, seats, bookingId,isPaid) VALUES (?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, time);
            ps.setString(3, title);
            ps.setInt(4, seats);
            ps.setString(5, null);
            ps.setBoolean(6, paid);
            ps.execute();
            ps.close();
            Booking booking = new Booking(date, time, title, seats, phoneNumber,paid);
            return booking;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //UPDATE ROW IN BOOKING TABLE BASED ON PHONE NUMBER
    public Booking updateBookingAfterPaid(String date, String time, String title, int seats, String phoneNumber,boolean paid) {
        try {
            String sql = "UPDATE Booking SET date=?, time=?, title=?, seats=?, phone_number=?,isPaid=? WHERE phone_number=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, date);
            ps.setString(2, time);
            ps.setString(3, title);
            ps.setInt(4, seats);
            ps.setString(5, phoneNumber);
            ps.setBoolean(6, paid);
            ps.setString(7, phoneNumber);

            int numberOfRows=ps.executeUpdate();
            System.out.println("Completed update. Number of rows affected:" + numberOfRows);
            ps.close();
            Booking booking = new Booking(date, time, title, seats, phoneNumber,paid);
            return booking;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Schedule getSchedule(String movieName) {
        AddBookingViewController addBookingViewController = new AddBookingViewController();
        try {
            String query = "SELECT * FROM Movie INNER JOIN schedule ON Movie.indexMovie= schedule.indexMovie WhERE Movie.title=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, movieName);
            ResultSet results = preparedStatement.executeQuery();
            Schedule schedule = new Schedule();
            while (results.next()){
                //CHANGE HEREEEEE

                for (int i = 1; i < 10; i++){
                    schedule.parseSchedule(addBookingViewController.convertWeek(results.getString("week" + i)), i);
                }
            }

            return schedule;



        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Booking getBooking(String phoneNumber){
        String out = "";
        Booking booking=null;
        try {
            String query = "SELECT * FROM Booking1 WHERE bookingId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, phoneNumber);
            ResultSet results = preparedStatement.executeQuery();

            while(results.next()){
                booking = new Booking (results.getString(1),results.getString(2),results.getString(3),results.getInt(4),results.getString(5),results.getBoolean(6));
            }



            return booking;



        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Snacks getSnacks(String phoneNumber){
        String out = "";
        Snacks snacks=null;
        try {
            String query = "SELECT smallCandy, largeCandy, smallDrink, largeDrink FROM drinkSnacks1 WHERE bookingId=?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, phoneNumber);
            ResultSet results = preparedStatement.executeQuery();

            while(results.next()){
                snacks = new Snacks (results.getInt(1),results.getInt(2),results.getInt(3),results.getInt(4));
            }



            return snacks;



        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePaid(boolean paid, String phoneNr) {

        String sql = "UPDATE Booking1 SET isPaid=? WHERE bookingId = ?";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setBoolean(1, paid);
            preparedStatement.setString(2, phoneNr);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateSeat(String seat, String phoneNr) {
        String sql = "UPDATE Booking1 SET seats=? WHERE bookingId = ?";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, seat);
            preparedStatement.setString(2, phoneNr);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateTime(String time, String phoneNr) {
        String sql = "UPDATE Booking1 SET time=? WHERE bookingId = ?";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, time);
            preparedStatement.setString(2, phoneNr);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateDate(String date, String phoneNr) {
        String sql = "UPDATE Booking1 SET date=? WHERE bookingId = ?";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, phoneNr);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateTitle(String title, String phoneNr) {
        String sql = "UPDATE Booking1 SET title=? WHERE bookingId = ?";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, phoneNr);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public int getId() {
        String out = "";
        Booking booking = null;
        int id = 0;


        String query = "SELECT bookingId FROM Booking1 ORDER BY bookingId DESC LIMIT 1;";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                id = results.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  id;

    }









}
