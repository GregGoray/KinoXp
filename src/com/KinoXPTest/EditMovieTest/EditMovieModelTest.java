package com.KinoXPTest.EditMovieTest;

import com.KinoXP.model.EditMovieViewModel;
import com.KinoXP.model.LoginViewModel;
import com.KinoXP.model.MovieTheaterModel;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Girts Zemitis on 25/02/2016.
 * https://github.com/GirtsZemitis
 */
public class EditMovieModelTest extends TestCase {



    @org.junit.Test
    public void testManageMovie() {


        LoginViewModel viewModel = new LoginViewModel();
        viewModel.connectToDatabase();
        EditMovieViewModel emvController = new EditMovieViewModel();

        try {
            Connection conn = LoginViewModel.conn;
            String sql = "INSERT INTO Movie (title, playingTimeMin, releaseYear, plot, director, posterPath, mainActor, cinemaRoomName, ganre, ageLimit) VALUES (?,?,?,?,?,?,?,?,?,?);";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, "Title");
                ps.setInt(2, 60);
                ps.setInt(3, 1994);
                ps.setString(4, "plott");
                ps.setString(5, "director");
                ps.setString(6, "posterPath");
                ps.setString(7, "main Actor");
                ps.setString(8, "cinemaRoomName");
                ps.setString(9, "ganre");
                ps.setInt(10, 10);
                ps.execute();
                ps.close();} catch (SQLException e) {
                e.printStackTrace();
            }

        String editTitle = emvController.editTitle("Title", "EditedTitle");
        assertNotNull(editTitle);
        assertEquals("EditedTitle", editTitle);

        int playingTime = emvController.editPlayingInMinutes("EditedTitle", 200);
        int releaseYear = emvController.editReleaseYear("EditedTitle", 10);
        String plot = emvController.editPlot("EditedTitle", "EditedPlot");
        String director = emvController.editDirector("EditedTitle", "EditedDirector");
        String mainActor = emvController.editMainActor("EditedTitle", "EditedMainActor");
        MovieTheaterModel editedMovieTheater = new MovieTheaterModel();
        editedMovieTheater.setName("new name");
        String editedMovieTheaterName = emvController.editMovieTheater("EditedTitle", editedMovieTheater.getName());
        String genre = emvController.editGenre("EditedTitle", "editedGenre");
        int ageLimit = emvController.editAgeLimit("EditedTitle", 14);

        assertNotNull(playingTime);
        assertEquals(200, playingTime);

        assertEquals(10, releaseYear);

        assertNotNull(plot);
        assertEquals("EditedPlot", plot);

        assertNotNull(director);
        assertEquals("EditedDirector", director);


        assertNotNull(mainActor);
        assertEquals("EditedMainActor", mainActor);


        assertNotNull(editedMovieTheaterName);
        assertEquals("new name", editedMovieTheater.getName());

        assertNotNull(genre);
        assertEquals("editedGenre", genre);

        assertEquals(14, ageLimit);

        try {
            Connection conn = LoginViewModel.conn;
            String sql = "DELETE FROM Movie WHERE title='EditedTitle';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ps.close();}
        catch (SQLException e) {
            e.printStackTrace();
        }

    }




}
