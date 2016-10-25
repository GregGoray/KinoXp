package com.KinoXP.model;

import com.sun.jndi.toolkit.url.Uri;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

/**
 * Created by Paula on 26-02-2016.
 */
public class AddMovieFormViewModel {
    private static Connection conn = LoginViewModel.conn;
    String posterPathString;
    String titleString;

    //METHOD FOR INSERTING THE MOVIE INTO THE DATABASE
    public void insertMovie(String title, String playingTimeInMinutes, String year, String plot, String director,
                            String posterPath, String cast, String theatreName, String genre, String ageLimit,Date date) {

        posterPathString=posterPath;
        titleString=title;

        String sql = "INSERT INTO Movie VALUES (null,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, playingTimeInMinutes);
            preparedStatement.setString(3, year);
            preparedStatement.setString(4, plot);
            preparedStatement.setString(5, director);
            preparedStatement.setString(6, posterPath);
            preparedStatement.setString(7, cast);
            preparedStatement.setString(8, theatreName);
            preparedStatement.setString(9, genre);
            preparedStatement.setString(10, ageLimit);
            preparedStatement.setDate(11, date);


            int numberOfRows = preparedStatement.executeUpdate();
            System.out.println("New title was successfully added to the database: " + numberOfRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //METHOD USED FOR TESTING THE CLASS
    public String getActorFromDatabase(String mainActor) {
        String out = "";
        try {
            String query = " SELECT title FROM Movie WHERE mainActor=? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, mainActor);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                out = results.getString(1);
            } else {
                out = "";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return out;
    }


    public String getPosterPathFromDatabase(String mainActor) {
        String out = "";


       /* try {
            String query = " SELECT title FROM Movie WHERE mainActor=? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, mainActor);
            ResultSet results = preparedStatement.executeQuery();

            if (results.next()) {
                out = results.getString(1);
            } else {
                out = "";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return out;
    }




}