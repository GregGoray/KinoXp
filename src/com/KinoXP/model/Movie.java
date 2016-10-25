package com.KinoXP.model;

import java.sql.Date;

/**
 * Created by krystian on 2016-03-01.
 */
public class Movie {
    public String getTitle() {
        return title;
    }
    public Movie(String title, int movieId){
        this.title = title;
        this.movieId = movieId;
    }
    public Movie(String title, int movieId, Date date){
        this.title = title;
        this.movieId = movieId;
        this.date = date;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    public String toString(){
        return title;
    }

    String title;
    int movieId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Date date;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public Movie(String title, int movieId, Date date, int duration) {
        this.title = title;
        this.movieId = movieId;
        this.date = date;
        this.duration = duration;
    }

    int duration;
}
