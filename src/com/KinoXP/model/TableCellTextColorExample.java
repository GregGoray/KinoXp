package com.KinoXP.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Created by Krys on 25/02/2016.
 */
public class TableCellTextColorExample {//try to make some structure here, it does not belong to view package!!


        public static class TableData {
            SimpleStringProperty one,two,three;
            public TableData(String one, String two, String three) {
                this.one = new SimpleStringProperty(one);
                this.two = new SimpleStringProperty(two);
                this.three = new SimpleStringProperty(three);
            }
           

        }



        public void start() {


            TableView<TableData> myTable = new TableView<TableData>();
            ObservableList<TableData> myTableData = FXCollections.observableArrayList(
                    new TableData("data", "data", "data"),
                    new TableData("data", "data", "data"),
                    new TableData("Name the song","867-5309","SomeEmail@gmail.com"));

            TableColumn firstColumn = new TableColumn("First Column");
            firstColumn.setCellValueFactory(new PropertyValueFactory<TableData,String>("one"));

            TableColumn secondColumn = new TableColumn("Second Column");
            secondColumn.setCellValueFactory(new PropertyValueFactory<TableData,String>("two"));

            TableColumn thirdColumn = new TableColumn("Third Column");
            thirdColumn.setCellValueFactory(new PropertyValueFactory<TableData,String>("three"));

            // ** The TableCell class has the method setTextFill(Paint p) that you
            // ** need to override the text color
            //   To obtain the TableCell we need to replace the Default CellFactory
            //   with one that returns a new TableCell instance,
            //   and @Override the updateItem(String item, boolean empty) method.
            //
            thirdColumn.setCellFactory(new Callback<TableColumn, TableCell>() {
                public TableCell call(TableColumn param) {
                    return new TableCell<TableData, String>() {

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                this.setTextFill(Color.RED);
                                // Get fancy and change color based on data
                                if(item.contains("@"))
                                    this.setTextFill(Color.BLUEVIOLET);
                                setText(item);
                            }
                        }
                    };
                }
            });


        }
    }



