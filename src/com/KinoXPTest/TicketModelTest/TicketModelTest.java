package com.KinoXPTest.TicketModelTest;

import com.KinoXP.model.Booking;
import com.KinoXP.model.LoginViewModel;
import com.KinoXP.model.TicketModel;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;


public class TicketModelTest {
    TicketModel ticketModel;

    @Before
    public void connectToDatabase(){
        LoginViewModel viewModel = new LoginViewModel();
        viewModel.connectToDatabase();
    }

    @Test
    public void testGetMovies() {
        ticketModel = new TicketModel();
        ObservableList<String> movies = ticketModel.getMovies();
        assertEquals(4, movies.size());
        //System.out.println(movies.size());
    }

    @Test
    public void testGetBookingByPhoneNumber() throws Exception {
        ticketModel = new TicketModel();
        ObservableList<Booking> bookingsByPhoneNumber = ticketModel.getBookingByPhoneNumber("33");
        assertNotNull(bookingsByPhoneNumber);
    }

    @Test
    public void testGetBookingByTitle() throws Exception {
        ticketModel = new TicketModel();
        ObservableList<Booking> bookingsByTitle = ticketModel.getBookingByTitle("Mask");
        assertNotNull(bookingsByTitle);
    }

    @Test
    public void testGetMovieByDateTime() throws Exception {
        ticketModel = new TicketModel();
        ObservableList<Booking> bookingsByDateTime = ticketModel.getMovieByDateTime("22.10.20", "10:00", "Mask");
        assertNotNull(bookingsByDateTime);
    }
}