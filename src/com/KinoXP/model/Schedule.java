package com.KinoXP.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hartyandi on 3/2/16.
 */
public class Schedule {

    private String title;
    private int id;
                //week      //day           //times
    private ArrayList<Map<String,ArrayList<String>>>schedule = new ArrayList<>();
    private String theater;

    /*public Schedule(String title, int id, Map<String, Map<ArrayList<String>, String>> schedule, String theater) {
        this.title = title;
        this.id = id;
        this.schedule = schedule;
        this.theater = theater;
    }*/

    public ArrayList<Map<String,ArrayList<String>>> getSchedule() {
        return schedule;
    }



    static String scheduleee =
           "9:00_yes_no_no_no_no_no_no\n" +
                   "9:30_yes_no_no_no_no_no_no\n" +
                   "10:00_yes_no_no_no_no_no_no\n" +
                   "10:30_yes_no_no_no_no_no_no\n" +
                   "11:00_no_no_no_no_no_no_no\n" +
                   "11:30_no_no_no_no_no_no_no\n" +
                   "12:00_no_no_no_no_no_no_no\n" +
                   "12:30_no_no_no_no_no_no_no\n" +
                   "13:00_no_no_no_no_no_no_no\n" +
                   "13:30_no_no_no_no_no_no_no\n" +
                   "14:00_no_no_no_no_no_no_no\n" +
                   "14:30_no_no_no_no_no_no_no\n" +
                   "15:00_no_no_no_no_no_no_no\n" +
                   "15:30_no_no_no_no_no_no_no\n" +
                   "16:00_no_no_no_no_no_no_no\n" +
                   "16:30_no_no_no_no_no_no_no\n" +
                   "17:00_no_no_no_no_no_no_no\n" +
                   "17:30_no_no_no_no_no_no_no\n" +
                   "18:00_no_no_no_no_no_no_no\n" +
                   "18:30_no_no_no_no_no_no_no\n" +
                   "19:00_no_no_no_no_no_no_no\n" +
                   "19:30_no_no_no_no_no_no_no\n" +
                   "20:00_no_no_no_no_no_no_no\n" +
                   "20:30_no_no_no_no_no_no_no\n" +
                   "21:00_no_no_no_no_no_no_no\n" +
                   "21:30_no_no_no_no_no_no_no\n" +
                   "22:00_no_no_no_no_no_no_no\n" +
                   "22:30_no_no_no_no_no_no_no";

    public ArrayList<Map<String,ArrayList<String>>> parseSchedule(String schedule, int week){
        ArrayList<String> timesStrings = new ArrayList<>();
        int nineCounter = 1;
        for (int i = 5; i < schedule.length(); i++){
            if (schedule.charAt(i) == '1' || schedule.charAt(i) == '2'){
                if (nineCounter == 2){
                    timesStrings.add(schedule.substring(5, i - 1));
                    schedule = schedule.substring(i, schedule.length());
                    i = 5;
                    nineCounter = -1;
                } else {
                    timesStrings.add(schedule.substring(6, i - 1));
                    schedule = schedule.substring(i, schedule.length());
                    i = 5;
                }


            } else if (schedule.charAt(i) == '9'){
                timesStrings.add(schedule.substring(5, i - 1));
                schedule = schedule.substring(i, schedule.length());
                i = 5;
                nineCounter++;
            }else if (i == schedule.length() - 1){
                timesStrings.add(schedule.substring(6));
            }
        }

        Map<String, ArrayList<String>> tempMap = new TreeMap<>();
        for (int day = 1; day < 8; day++) {
            ArrayList<String> times = new ArrayList<>();

            for (int i = 0; i < timesStrings.size(); i++) {
                if (timesStrings.get(i).charAt(0) == 'y') {
                    times.add(Integer.toString(i));
                    if (day < 7) {
                        timesStrings.set(i, timesStrings.get(i).substring(4, timesStrings.get(i).length()));
                    }

                } else {
                   if (day < 7) {
                       timesStrings.set(i, timesStrings.get(i).substring(3, timesStrings.get(i).length()));
                   }
                }

            }
            tempMap.put(Integer.toString(day), times);
        }

        this.schedule.add(week - 1, tempMap);
        return this.schedule;
    }

    static public void main(String []arfs){
        Schedule schedule = new Schedule();
        System.out.println(schedule.parseSchedule(scheduleee, 1));
    }
}
