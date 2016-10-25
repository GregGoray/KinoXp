package com.KinoXP.model;

/**
 * Created by hartyandi on 2/24/16.
 */
public class MovieModel {

    private String titel;
    private int playingtimeInMinutes;
    private String realeseYear;
    private String plot;
    private String director;
    private String posterpath;
    private String mainActor;
    private MovieTheaterModel movieTheater;

    public MovieModel(String titel, int playingtimeInMinutes, String realeseYear, String plot, String director, String posterpath, String mainActor, MovieTheaterModel movieTheater) {
        this.titel = titel;
        this.playingtimeInMinutes = playingtimeInMinutes;
        this.realeseYear = realeseYear;
        this.plot = plot;
        this.director = director;
        this.posterpath = posterpath;
        this.mainActor = mainActor;
        this.movieTheater = movieTheater;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getPlayingtimeInMinutes() {
        return playingtimeInMinutes;
    }

    public void setPlayingtimeInMinutes(int playingtimeInMinutes) {
        this.playingtimeInMinutes = playingtimeInMinutes;
    }

    public String getRealeseYear() {
        return realeseYear;
    }

    public void setRealeseYear(String realeseYear) {
        this.realeseYear = realeseYear;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public MovieTheaterModel getMovieTheater() {
        return movieTheater;
    }

    public void setMovieTheater(MovieTheaterModel movieTheater) {
        this.movieTheater = movieTheater;
    }
}
