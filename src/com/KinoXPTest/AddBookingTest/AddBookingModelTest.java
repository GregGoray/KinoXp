package com.KinoXPTest.AddBookingTest;

import com.KinoXP.model.AddBookingViewModel;
import com.KinoXP.model.Booking;
import com.KinoXP.model.LoginViewModel;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Girts Zemitis on 01/03/2016.
 * https://github.com/GirtsZemitis
 */
public class AddBookingModelTest extends TestCase {

    LoginViewModel loginViewModel = new LoginViewModel();

    @org.junit.Test
    public void testAddBooking() {


        LoginViewModel viewModel = new LoginViewModel();
        viewModel.connectToDatabase();
        AddBookingViewModel addBookingViewModel = new AddBookingViewModel();
        // String DB_URL = "jdbc:mysql://localhost/testkinoxp";

        Booking booking = addBookingViewModel.insertBooking("Date", "Time", "Title", 3, "999999999",true);

        assertNotNull(booking);
        assertEquals("Date", booking.getDate());
        assertEquals("Time", booking.getTime());
        assertEquals("Title", booking.getTitle());
        assertEquals(3, booking.getSeats());
        assertEquals("999999999", booking.getPhoneNumber());

        try {
            Connection conn = LoginViewModel.conn;
            String sql = "DELETE FROM Booking WHERE phone_number=999999999;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ps.close();}
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Before
    public void connectToDatabase() {
        loginViewModel.connectToDatabase();
    }
    @Test
    public void testUpdateBooking(){

        AddBookingViewModel abvm = new AddBookingViewModel();
        Booking booking = new Booking("date", "time", "title", 2, "7777777",true);
        //assertNotNull(abvm.updateBookingAfterPaid("date", "time", "title", 2, "7777777",true));
        //assertEquals(booking.getPhoneNumber(),abvm.updateBookingAfterPaid("date", "time", "title", 2, "7777777",true).getPhoneNumber());
    }






}

