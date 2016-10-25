package com.KinoXP.controller;

import com.KinoXP.model.Booking;
import com.KinoXP.model.TicketModel;
import com.KinoXP.view.TicketView;
import javafx.collections.ObservableList;

/**
 * Created by Lucia/Paula on 05/03/2016.
 */
public class TicketController {
    TicketModel ticketModel;
    TicketView ticketView;

    public TicketController(TicketModel ticketModel, TicketView ticketView){
        this.ticketModel = ticketModel;
        this.ticketView = ticketView;
    }

    public TicketController(){}

    //GET BOOKING BY PHONE NUMBER
    public ObservableList<Booking> returnBookingByPhoneNumber(java.lang.String phoneNumber) {
        ObservableList<Booking> tab = ticketModel.getBookingByPhoneNumber(phoneNumber);
        return tab;
    }

    //GET BOOKING BY TITLE
    public ObservableList<Booking> returnBookingByTitle(java.lang.String title) {
        ObservableList<Booking> tab = ticketModel.getBookingByTitle(title);
        return tab;
    }

    //GET BOOKING BY TITLE, DATE AND TIME
    public ObservableList<Booking> returnBookingByDateTime(java.lang.String date, java.lang.String time, java.lang.String title) {
        ObservableList<Booking> tab = ticketModel.getMovieByDateTime(date, time, title);
        return tab;
    }

    //GET BOOKING BY PAID TICKET
    public ObservableList<Booking> returnBookingByPaidTicket(java.lang.String checkBox) {
        ObservableList<Booking> tab = ticketModel.getBookingByPaidTicket(checkBox);
        return tab;
    }

    //GET BOOKING BY RESERVED TICKET
    public ObservableList<Booking> returnBookingByUnpaidTicket(java.lang.String checkBox) {
        ObservableList<Booking> tab = ticketModel.getBookingByUnpaidTicket(checkBox);
        return tab;
    }

    //GET BOOKING BY THEATER
    public ObservableList<Booking> returnBookingByTheater(java.lang.String theatre) {
        ObservableList<Booking> tab = ticketModel.getBookingByTheater(theatre);
        return tab;
    }

}
