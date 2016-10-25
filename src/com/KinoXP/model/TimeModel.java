package com.KinoXP.model;

/**
 * Created by krystian on 2016-02-24.
 */
public class TimeModel {

    String time;
    String monday;
    String tuesday;
    String wednesday;

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getThrusday() {
        return thrusday;
    }

    public void setThrusday(String thrusday) {
        this.thrusday = thrusday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    String thrusday;
    String friday;
    String saturday;
    String sunday;

    public TimeModel(String time, String monday, String tuesday, String wednesday, String thrusday, String friday, String saturday,
                     String sunday){

        this.time = time;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thrusday = thrusday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
