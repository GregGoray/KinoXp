package com.KinoXP.controller;

import com.KinoXP.model.*;
import com.KinoXP.view.AddBookingView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by MazurJestBoski on 2016-02-29.
 */
public class AddBookingViewController {

    AddBookingViewModel addBookingViewModel;
    Connection conn = LoginViewModel.conn;

    public AddBookingViewController() {
       this.addBookingViewModel = new AddBookingViewModel();

    }


    public void addBookingViewDisplay(){
        AddBookingView addBookingView = new AddBookingView();
        addBookingView.start();
    }

    public Booking getBookingByPhoneNUmber(String phoneNumber){
        return addBookingViewModel.getBooking(phoneNumber);

    }

    public Snacks getSnacks(String phoneNumber){
        return addBookingViewModel.getSnacks(phoneNumber);
    }

    public ObservableList<String> getMoviesWithSchedule() {
        try {
            String query = "SELECT Movie.title FROM Movie INNER JOIN schedule ON Movie.indexMovie= schedule.indexMovie";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet results = preparedStatement.executeQuery();
            ArrayList<String> arrayList = new ArrayList<>();
            while (results.next()){
                arrayList.add(results.getString(1));
            }
            ObservableList<String> movieTitles =  FXCollections.observableArrayList(arrayList);



            return movieTitles;



        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Schedule getSchedule(String title){
        return addBookingViewModel.getSchedule(title);
    }


    public ObservableList getDateFieldInfo(String title) {
        Schedule schedule = addBookingViewModel.getSchedule(title);
        ArrayList<String> weeksAndDays = new ArrayList<String>();
        for (int i = 0; i < schedule.getSchedule().size(); i++) {
            for (int j = 0; j < 7; j++) {
                if (schedule.getSchedule().get(i).get(Integer.toString(j)) == null) {

                } else if (!schedule.getSchedule().get(i).get(Integer.toString(j)).isEmpty()) {
                    weeksAndDays.add("Day:" + j + " Week:" + (i + 1));
                }
            }
        }
        return FXCollections.observableArrayList(weeksAndDays);
    }

    public ObservableList<String> getTimes(int day, int week, Schedule schedule) {

        ArrayList<String> times = new ArrayList<>();
        for (int i = 0; i < schedule.getSchedule().get(week - 1).get(Integer.toString(day)).size(); i++){
            times.add(schedule.getSchedule().get(week - 1).get(Integer.toString(day)).get(i));
        }


        return FXCollections.observableArrayList(times);
    }

    public void insertBooking(String date,String time,String title,int seats,String phonenumber,boolean paid){

        addBookingViewModel.insertBooking(date,time,title,seats,phonenumber,paid);
    }
    //CALL UPDATE BOOKING METHOD
    public void updateBooking(String date,String time,String title,int seats,String phonenumber,boolean paid){
        addBookingViewModel.updateBookingAfterPaid(date,time,title,seats,phonenumber,paid);
    }

    public ObservableList<String> parseTimes(ObservableList<String> times) {
        for (int i = 0; i < times.size(); i++){
            switch (times.get(i)) {
                case "0":
                    times.set(i, "9:00");
                    break;
                case "1":
                    times.set(i, "9:30");
                    break;
                case "2":
                    times.set(i, "10:00");
                    break;
                case "3":
                    times.set(i, "10:30");
                    break;
                case "4":
                    times.set(i, "11:00");
                    break;
                case "5":
                    times.set(i, "11:30");
                    break;
                case "6":
                    times.set(i, "12:00");
                    break;
                case "7":
                    times.set(i, "12:30");
                    break;
                case "8":
                    times.set(i, "13:00");
                    break;
                case "9":
                    times.set(i, "13:30");
                    break;
                case "10":
                    times.set(i, "14:00");
                    break;
                case "11":
                    times.set(i, "14:30");
                    break;
                case "12":
                    times.set(i, "15:00");
                    break;
                case "13":
                    times.set(i, "15:30");
                    break;
                case "14":
                    times.set(i, "16:00");
                    break;
                case "15":
                    times.set(i, "16:30");
                    break;
                case "16":
                    times.set(i, "17:00");
                    break;
                case "17":
                    times.set(i, "17:30");
                    break;
                case "18":
                    times.set(i, "18:00");
                    break;
                case "19":
                    times.set(i, "18:30");
                    break;
                case "20":
                    times.set(i, "19:00");
                    break;
                case "21":
                    times.set(i, "19:30");
                    break;
                case "22":
                    times.set(i, "20:00");
                    break;
                case "23":
                    times.set(i, "20:30");
                    break;
                case "24":
                    times.set(i, "21:00");
                    break;
                case "25":
                    times.set(i, "21:30");
                    break;
                case "26":
                    times.set(i, "22:00");
                    break;
                case "27":
                    times.set(i, "22:30");
                    break;
            }
    }
        return times;
    }

    public void updatePaid(boolean isPaid, String phonenr){
        addBookingViewModel.updatePaid(isPaid, phonenr);
    }
    public void updateSeat(String seatNr, String phonenr){
        addBookingViewModel.updateSeat(seatNr, phonenr);
    }
    public void updateTitle(String title, String phonenr){
        addBookingViewModel.updateTitle(title, phonenr);
    }
    public void updateDate(String date, String phonenr){
        addBookingViewModel.updateDate(date, phonenr);
    }
    public void updateTime(String time, String phonenr){
        addBookingViewModel.updateTime(time, phonenr);
    }

    // it doesnt find getSchedule in model
    /*public Booking getSchedule(String phoneNumber){
        return addBookingViewModel.getSchedule();

    }*/
    //convert new week into old no_yes
    public String convertWeek(String s){
        String newSchedule ="";
        for(int i =0; i<s.length(); i++) {
            if (s.charAt(i) == 'y' || s.charAt(i) == 'n') {
                if (s.charAt(i) == 'y') {
                    newSchedule += s.charAt(i);
                    newSchedule += "es";


                } else {
                    newSchedule += s.charAt(i);
                    newSchedule += "o";

                }

            } else {
                newSchedule += s.charAt(i);

            }
        }
        return  newSchedule;
    }
    public int getId(){
        return addBookingViewModel.getId();
    }

}
