package com.KinoXP.controller;

import com.KinoXP.model.*;
import com.KinoXP.view.MenuView;
import com.KinoXP.view.Schedule;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * Created by hartyandi on 2/24/16.
 */
public class ManageMovieScheduleController {
    ArrayList<MovieWeek> arrayList;
    ManageMovieSceduleModel manageMovieSceduleModel = new ManageMovieSceduleModel();
    Schedule schedule;
    int weekCounter = 1;
    Movie movie;
    int weekFromDb;
    int weekLimit;
    int howManyCell;
    TableForSchedule tableForSchedule;

    public ManageMovieScheduleController() {

    }

    public ManageMovieScheduleController(Schedule schedule, Movie movie) {
        this.schedule = schedule;
        this.movie = movie;
        weekFromDb = getWeekFromDb(movie.getDate());
        weekLimit = weekFromDb + 12;
        tableForSchedule = new TableForSchedule(this,howManyCell(movie.getDuration()));
        getArrayList();
    }

    public void scheduleDisplay() {
        Schedule schedule = new Schedule();
        schedule.start();
    }

    public void scheduleFromTheatre() {
        Schedule schedule = new Schedule();
        schedule.startSchedule();
    }


    public void scheduleDisplay(Movie m) {
        Schedule schedule = new Schedule(m);
        schedule.start();
    }

    public ArrayList<MovieWeek> getArrayList() {
        arrayList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            arrayList.add(new MovieWeek());
        }
        return arrayList;
    }

    public void saveSchedule() {

        manageMovieSceduleModel.saveSchedule(arrayList.get(weekCounter-1).save(arrayList.get(weekCounter-1).getObservableListFromDb()) ,weekCounter,movie.getMovieId());
    }

    public String printScheduleFromDb(int i) {
        return manageMovieSceduleModel.getSchedule(i);
    }


    public String getScheduleForMovie(String s, int i) {
        return manageMovieSceduleModel.getScheduleForMovie(s, i);
    }

    public ObservableList<Movie> getMovieTitles(String s) {
        return manageMovieSceduleModel.getMovieTitles(s);
    }

    public void insertMovie(String s) {
        manageMovieSceduleModel.InsertDefaultSchedule(s);

    }

    public int getWeekFromDb(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int weekFromDb = cal.get(Calendar.WEEK_OF_YEAR);
        return weekFromDb;
    }

    public ObservableList getObservableListForWeek(int i, String s) {
        ObservableList<TimeModel> observableList = arrayList.get(i).readFromDb(getScheduleForMovie(s, i + 1));
        return observableList;
    }

    public TableView getTable() {
        return tableForSchedule.getTable();
    }

    public void startMenuView() {
        MenuView menuView = new MenuView();
        menuView.start();
        schedule.getPrimaryStage().close();

    }

    public int getWeekFromDb() {
        return weekFromDb;
    }

    public void setWeekFromDb() {
        weekFromDb = getWeekFromDb(movie.getDate());

    }

    public void nextButtonAction() {
        //Incremant week which is in label
        weekFromDb++;
        weekCounter++;
        if (weekFromDb < weekLimit) {
            schedule.getTable().setItems(getObservableListForWeek(weekCounter-1, movie.getTitle()));
            setNrOfWeek(weekFromDb);

        }else {
            weekCounter = 1;
            weekFromDb = weekLimit-12;
            schedule.getTable().setItems(getObservableListForWeek(weekCounter-1, movie.getTitle()));
            //update label
            setNrOfWeek(weekFromDb);
        }
    }

    public void setNrOfWeek(int i){
        schedule.getNrOfWeek().setText("Week " +i);

    }

    public void preButtonAction() {
        if (weekCounter > 1) {

            weekFromDb--;
            weekCounter--;
            schedule.getTable().setItems(getObservableListForWeek(weekCounter-1, movie.getTitle()));
            setNrOfWeek(weekFromDb);


        } else {
            weekCounter = 12;
            weekFromDb = weekLimit;
            schedule.getTable().setItems(getObservableListForWeek(weekCounter-1, movie.getTitle()));
            setNrOfWeek(weekFromDb);
        }
    }
    public void setItems(){
       schedule.getTable().setItems(arrayList.get(weekCounter-1).readFromDb(getScheduleForMovie(movie.getTitle(),1)));;
    }
    public void setYestDb(TimeModel timeModel, int row) {
        arrayList.get(weekCounter - 1).setYesDb(timeModel, row);
        schedule.getTable().setItems(arrayList.get(weekCounter - 1).getObservableListFromDb());
    }
    public int howManyCell(int i){
        int howManyCell = i/30;
        return howManyCell;
    }
}