package com.KinoXP.model;

import com.KinoXP.controller.ManageMovieScheduleController;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * Created by krystian on 2016-03-07.
 */
public class TableForSchedule{
    TableView table;
    //columns for table
    TableColumn<TimeModel, String> hour;



    TableColumn monday;
    TableColumn tuesday;
    TableColumn wednesday;
    TableColumn thursday;
    TableColumn friday;
    TableColumn saturday;
    TableColumn sanday;

    int column = 0;
    int row = 0;
    TimeModel timeModel;
    ManageMovieScheduleController manageMovieScheduleController;
    int howManyCells;
    public TableView getTable() {
        return table;
    }

    public void setTable(TableView table) {
        this.table = table;
    }


    public TableForSchedule(ManageMovieScheduleController manageMovieScheduleController, int howManyCells){
        this.manageMovieScheduleController = manageMovieScheduleController;
        this.howManyCells = howManyCells;
        table = new TableView<TimeModel>();
        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        //create hour column with style
        setHour();
        setMonday();
        setTuesday();
        setWednesday();
        setThursday();
        setFriday();
        setSaturday();
        setSanday();
        table.getColumns().addAll(hour, monday, tuesday, wednesday, thursday, friday, saturday, sanday);
        setSizeForColumns();
        setActionForTable();



    }

    public void setHour(){

        hour  = new TableColumn("Hours");
        hour.setStyle("-fx-background-color: #cfcbd0;");
        hour.setCellValueFactory(new PropertyValueFactory<>("time"));
    }

    public void setMonday() {
        monday = new TableColumn("Monday");
        monday.setStyle("-fx-background-color: #cfcbd0;" +
                "       -fx-border-style: solid;" +
                "       -fx-border-color: #3d586f;");


        setFancyDesignForCell(monday,"monday");
    }

    public void setTuesday(){
        tuesday = new TableColumn("Tuesday");
        tuesday.setStyle("-fx-background-color: #cfcbd0;" +
                "       -fx-border-style: solid;" +
                "       -fx-border-color: #3d586f;");

        setFancyDesignForCell(tuesday,"tuesday");

    }

    public void setWednesday(){
        wednesday = new TableColumn("Wednesday");
        wednesday.setStyle("-fx-background-color: #cfcbd0;" +
                "       -fx-border-style: solid;" +
                "       -fx-border-color: #3d586f;");
        setFancyDesignForCell(wednesday,"wednesday");
    }
    public void setThursday(){
        thursday = new TableColumn("Thursday");
        thursday.setStyle("-fx-background-color: #cfcbd0;" +
                "       -fx-border-style: solid;" +
                "       -fx-border-color: #3d586f;");
        setFancyDesignForCell(thursday,"thrusday");
    }
    public void setFriday(){
        friday = new TableColumn("Friday");
        friday.setStyle("-fx-background-color: #cfcbd0;" +
                "       -fx-border-style: solid;" +
                "       -fx-border-color: #3d586f;");
        setFancyDesignForCell(friday,"friday");
    }
    public void setSaturday(){
        saturday = new TableColumn("Saturday");
        saturday.setStyle("-fx-background-color: #cfcbd0;" +
                "       -fx-border-style: solid;" +
                            "       -fx-border-color: #3d586f;");
                    setFancyDesignForCell(saturday,"saturday");
                }
            public void setSanday(){
                sanday = new TableColumn("Sanday");
                sanday.setStyle("-fx-background-color: #cfcbd0;" +
                        "       -fx-border-style: solid;" +
                        "       -fx-border-color: #3d586f;");
                setFancyDesignForCell(sanday,"sunday");
            }

            public void setFancyDesignForCell(TableColumn columnName,String valueFactory){

                columnName.setCellValueFactory(new PropertyValueFactory<TimeModel, String>(valueFactory));
                columnName.setCellFactory(new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn param) {
                        return new TableCell<TableCellTextColorExample.TableData, String>() {

                            @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            if (item.contains("n")) {
                                this.setTextFill(Color.RED);
                                this.setStyle("-fx-background-color: #878588;" +
                                        "-fx-border-bottom-style: solid; " +
                                        "-fx-border-color: #979495;; " );



                            }
                            if (item.contains("y")) {
                                this.setTextFill(Color.GREEN);

                                this.setStyle("-fx-background-color: #343533;" +
                                        "-fx-border-bottom-style: solid; " +
                                        "-fx-border-color: #979495;; " );
                            }

                        }
                    }
                };
            }
        });
    }
    public void setSizeForColumns(){
        //set column size
        hour.prefWidthProperty().bind(table.widthProperty().divide(10)); // w * 1/4
        monday.prefWidthProperty().bind(table.widthProperty().divide(8)); // w * 1/4
        tuesday.prefWidthProperty().bind(table.widthProperty().divide(8)); // w * 1/4
        wednesday.prefWidthProperty().bind(table.widthProperty().divide(8)); // w * 1/4
        thursday.prefWidthProperty().bind(table.widthProperty().divide(8)); // w * 1/4
        friday.prefWidthProperty().bind(table.widthProperty().divide(8)); // w * 1/4
        saturday.prefWidthProperty().bind(table.widthProperty().divide(8)); // w * 1/4
        sanday.prefWidthProperty().bind(table.widthProperty().divide(8)); // w * 1/4
    }
    public void setActionForTable(){
           table.setOnMouseClicked(event -> {
              TablePosition firstCell = (TablePosition) table.getSelectionModel().getSelectedCells().get(0);
               column = firstCell.getColumn();
               row = firstCell.getRow();
               timeModel = (TimeModel) table.getSelectionModel().getSelectedItem();
               actionForColumns();

           });
        }

    public void actionForColumns(){
        if (column == 1) {
            if (timeModel.getMonday().equals("n")) {
               if(howManyCells>1){
                   for(int i =0; i<howManyCells; i++){

                       timeModel.setMonday("y");
                       manageMovieScheduleController.setYestDb(timeModel, row);
                       row++;
                       timeModel = (TimeModel)table.getItems().get(row);
                   }
               }else {
                   timeModel.setMonday("y");
                   manageMovieScheduleController.setYestDb(timeModel, row);
               }

            }else {

                timeModel = (TimeModel) table.getSelectionModel().getSelectedItem();
                timeModel.setMonday("n");
                manageMovieScheduleController.setYestDb(timeModel, row);

            }
        }

        if (column == 2)  {
            if (timeModel.getTuesday().equals("n")) {
                if(howManyCells>1){
                    for(int i =0; i<howManyCells; i++){

                        timeModel.setTuesday("y");
                        manageMovieScheduleController.setYestDb(timeModel, row);
                        row++;
                        timeModel = (TimeModel)table.getItems().get(row);
                    }
                }else {
                    timeModel.setTuesday("y");
                    manageMovieScheduleController.setYestDb(timeModel, row);
                }

            }else {

                timeModel = (TimeModel) table.getSelectionModel().getSelectedItem();
                timeModel.setTuesday("n");
                manageMovieScheduleController.setYestDb(timeModel, row);

            }
        }



            if (column == 3)  {
                if (timeModel.getWednesday().equals("n")) {
                    if(howManyCells>1){
                        for(int i =0; i<howManyCells; i++){

                            timeModel.setWednesday("y");
                            manageMovieScheduleController.setYestDb(timeModel, row);
                            row++;
                            timeModel = (TimeModel)table.getItems().get(row);
                        }
                    }else {
                        timeModel.setWednesday("y");
                        manageMovieScheduleController.setYestDb(timeModel, row);
                    }

                }else {

                    timeModel = (TimeModel) table.getSelectionModel().getSelectedItem();
                    timeModel.setWednesday("n");
                    manageMovieScheduleController.setYestDb(timeModel, row);

                }
            }
            if (column == 4) {
                if (timeModel.getThrusday().equals("n")) {
                    if(howManyCells>1){
                        for(int i =0; i<howManyCells; i++){

                            timeModel.setThrusday("y");
                            manageMovieScheduleController.setYestDb(timeModel, row);
                            row++;
                            timeModel = (TimeModel)table.getItems().get(row);
                        }
                    }else {
                        timeModel.setThrusday("y");
                        manageMovieScheduleController.setYestDb(timeModel, row);
                    }

                }else {

                    timeModel = (TimeModel) table.getSelectionModel().getSelectedItem();
                    timeModel.setThrusday("n");
                    manageMovieScheduleController.setYestDb(timeModel, row);

                }
            }
           if (column == 5)  {
               if (timeModel.getFriday().equals("n")) {
                   if(howManyCells>1){
                       for(int i =0; i<howManyCells; i++){

                           timeModel.setFriday("y");
                           manageMovieScheduleController.setYestDb(timeModel, row);
                           row++;
                           timeModel = (TimeModel)table.getItems().get(row);
                       }
                   }else {
                       timeModel.setFriday("y");
                       manageMovieScheduleController.setYestDb(timeModel, row);
                   }

               }else {

                   timeModel = (TimeModel) table.getSelectionModel().getSelectedItem();
                   timeModel.setFriday("n");
                   manageMovieScheduleController.setYestDb(timeModel, row);

               }
           }
           if (column == 6) {
               if (timeModel.getSaturday().equals("n")) {
                   if(howManyCells>1){
                       for(int i =0; i<howManyCells; i++){

                           timeModel.setSaturday("y");
                           manageMovieScheduleController.setYestDb(timeModel, row);
                           row++;
                           timeModel = (TimeModel)table.getItems().get(row);
                       }
                   }else {
                       timeModel.setSaturday("y");
                       manageMovieScheduleController.setYestDb(timeModel, row);
                   }

               }else {

                   timeModel = (TimeModel) table.getSelectionModel().getSelectedItem();
                   timeModel.setSaturday("n");
                   manageMovieScheduleController.setYestDb(timeModel, row);

               }
           }
           if (column == 7) {
               if (timeModel.getSunday().equals("n")) {
                   if(howManyCells>1){
                       for(int i =0; i<howManyCells; i++){

                           timeModel.setSunday("y");
                           manageMovieScheduleController.setYestDb(timeModel, row);
                           row++;
                           timeModel = (TimeModel)table.getItems().get(row);
                       }
                   }else {
                       timeModel.setSunday("y");
                       manageMovieScheduleController.setYestDb(timeModel, row);
                   }

               }else {

                   timeModel = (TimeModel) table.getSelectionModel().getSelectedItem();
                   timeModel.setSunday("n");
                   manageMovieScheduleController.setYestDb(timeModel, row);

               }
           }

    }

}

