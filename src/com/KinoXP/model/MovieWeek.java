package com.KinoXP.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by krystian on 2016-02-25.
 */
public class MovieWeek {

    ObservableList<TimeModel> observableList;
    ObservableList<TimeModel> observableListFromDb;
    ObservableList<TimeModel> observableList2;
    LoginViewModel loginViewModel = new LoginViewModel();
    public MovieWeek(){
        observableList  = FXCollections.observableArrayList();
        observableList.add(new TimeModel("9:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("9:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("10:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("10:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("11:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("11:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("12:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("12:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("13:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("13:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("14:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("14:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("15:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("15:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("16:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("16:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("17:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("17:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("18:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("18:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("19:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("19:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("20:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("20:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("21:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("21:30","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("22:00","n","n","n","n","n","n","n"));
        observableList.add(new TimeModel("22:30","n","n","n","n","n","n","n"));
    }

    public ObservableList<TimeModel> getObservableList(){
        return observableList;
    }
    public void setYes(TimeModel timeModel, int i){
        observableList.set(i, timeModel);

    }
    public void setYesDb(TimeModel timeModel, int i){
        observableListFromDb.set(i,timeModel);
    }
    public void setYesDb(TimeModel timeModel,int i, int howMany){
        for(int b = 0; b < howMany; b++){
            observableListFromDb.set(i,timeModel);
            i++;
        }
    }

    public String save(ObservableList<TimeModel> observableList2){
        String save ="";

        this.observableList2 = observableList2;
        for(int i= 0; i<observableList2.size(); i++){
            save = save + observableList2.get(i).getTime() + "_" + observableList2.get(i).getMonday() +"_"+
                    observableList2.get(i).getTuesday() + "_"+observableList2.get(i).getWednesday() +"_"+
                    observableList2.get(i).getThrusday() +"_"+ observableList2.get(i).getFriday() +"_"+
                    observableList2.get(i).getSaturday() +"_"+ observableList2.get(i).getSunday() + "\n";


        }




        return save;
        //returning text from observablelist with _
    }


    public ObservableList<TimeModel> readFromDb(String s){
        String hour ="";
        String monday="";
        String tuesday ="";
        String wedensday="";
        String thursday="";
        String friday="";
        String saturday="";
        String sunday="";
        String weeknr="";
        char seperator = '_';
        int variableCounter = 0;




        observableListFromDb = FXCollections.observableArrayList();

        for(int i =0; i <s.length(); i++){
            if(s.charAt(i)=='-') {
                break;
            }


            if(variableCounter==0){
                if(s.charAt(i)!=seperator){
                    hour += s.charAt(i);
                }else {
                    i++;
                    variableCounter++;
                }
            }
            if(variableCounter==1){
                if(s.charAt(i)!=seperator){
                    monday += s.charAt(i);
                }else {
                    i++;
                    variableCounter++;
                }
            }
            if(variableCounter==2){
                if(s.charAt(i)!=seperator){
                    tuesday += s.charAt(i);
                }else {
                    i++;
                    variableCounter++;
                }
            }
            if(variableCounter==3){
                if(s.charAt(i)!=seperator){
                    wedensday += s.charAt(i);
                }else {
                    i++;
                    variableCounter++;
                }
            }
            if(variableCounter==4){
                if(s.charAt(i)!=seperator){
                    thursday += s.charAt(i);
                }else {
                    i++;
                    variableCounter++;
                }
            }
            if(variableCounter==5){
                if(s.charAt(i)!=seperator){
                    friday += s.charAt(i);
                }else {
                    i++;
                    variableCounter++;
                }
            }
            if(variableCounter==6){
                if(s.charAt(i)!=seperator){
                    saturday += s.charAt(i);
                }else {
                    i++;
                    variableCounter++;
                }
            }
            if(variableCounter==7){
                if(s.charAt(i)!='\n'){
                    sunday += s.charAt(i);
                }else {
                    variableCounter++;
                    observableListFromDb.add(new TimeModel(hour,monday,tuesday,wedensday,thursday,friday,saturday,sunday));
                    variableCounter=0;
                    hour="";
                    monday="";
                    tuesday ="";
                    wedensday="";
                    thursday="";
                    friday="";
                    saturday="";
                    sunday="";


                }

            }



        }
        System.out.println(weeknr);
        return observableListFromDb;
    }
    public ObservableList<TimeModel> getObservableListFromDb(){
        return observableListFromDb;
    }



}

