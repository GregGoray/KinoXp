package com.KinoXP.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
     * Created by Monica on 06-03-2016.
     */
    public class PriceViewModel {
        ExtrasModel extras;
        LoginViewModel loginViewModel;
        Connection conn = LoginViewModel.conn;

        public ExtrasModel retrievePricesOfExtras(String type){

            try {
                String query = "SELECT * FROM Extras WHERE Type=?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1,type);
                ResultSet results = preparedStatement.executeQuery();
                if(results.next()) {
                    ExtrasModel extras = new ExtrasModel(results.getString(1), results.getInt(2), results.getInt(3));
                    return  extras;
                }
            }catch (Exception e){
                System.out.println("Couldn't retrieve extras from db!");
            }
            return null;
        }
        public void changePrices(String type, int priceLarge, int priceSmall){
            try {
                String sql = "UPDATE Extras SET Type=?, XLPrice=?, SPrice=? WHERE Type=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, type);
                ps.setInt(2, priceLarge);
                ps.setInt(3, priceSmall);
                ps.setString(4, type);
                int numberOfRows=ps.executeUpdate();
                System.out.println("Completed update. Number of rows affected:" + numberOfRows);
                ps.close();
            }
            catch (SQLException e) {
                System.out.println("Couldn't change/update the prices! Sorry!");
            }
        }

    }


