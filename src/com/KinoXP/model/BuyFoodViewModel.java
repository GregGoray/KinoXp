package com.KinoXP.model;


import com.KinoXP.controller.AddBookingViewController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by MazurJestBoski on 2016-03-05.
 */
public class BuyFoodViewModel {

    Connection conn = LoginViewModel.conn;

    public void setFoodToDB(int phoneNumber, int sCandyCount, int lCandyCount, int sSodaCount, int lSodaCount) {

        String query;
        query = "INSERT into drinkSnacks1 VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, phoneNumber);
            preparedStatement.setInt(2, sCandyCount);
            preparedStatement.setInt(3, lCandyCount);
            preparedStatement.setInt(4, sSodaCount);
            preparedStatement.setInt(5, lSodaCount);
            preparedStatement.executeUpdate();

            System.out.println("snaki dodane łośki! ");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateLargeCandy(int largeCandy, int phoneNr) {
        String sql = "UPDATE drinkSnacks1 SET largeCandy=? WHERE bookingId = ?";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, largeCandy);
            preparedStatement.setInt(2, phoneNr);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateSmallCandy(int smallCandy, int phoneNr) {
        String sql = "UPDATE drinkSnacks1 SET smallCandy=? WHERE bookingId = ?";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, smallCandy);
            preparedStatement.setInt(2, phoneNr);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateLargeSoda(int largeSoda, int phoneNr) {
        String sql = "UPDATE drinkSnacks1 SET largeDrink=? WHERE bookingId = ?";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, largeSoda);
            preparedStatement.setInt(2, phoneNr);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateSmallSoda(int smallSoda, int phoneNr) {
        String sql = "UPDATE drinkSnacks1 SET smallDrink=? WHERE bookingId = ?";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, smallSoda);
            preparedStatement.setInt(2, phoneNr);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
