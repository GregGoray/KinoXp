package com.KinoXP.view;/**
 * Created by MazurJestBoski on 2016-02-29.
 */

import com.KinoXP.controller.AddBookingViewController;
import com.KinoXP.model.Booking;
import com.KinoXP.model.Schedule;
import com.KinoXP.model.Snacks;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import org.controlsfx.control.spreadsheet.Grid;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

public class AddBookingView {

    AddBookingViewController addBookingViewController = new AddBookingViewController();
    private Schedule schedule;

    public void start() {
        //LABELS
        Label mainLabel = new Label("Booking Manager");
        mainLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        Label searchLabel = new Label("Search By Reservation Number");
        Label dateLabel = new Label("Date");
        Label timeLabel = new Label("Time");
        Label titleLabel = new Label("Title");
        Label seatsAmountLabel = new Label("Seats amount");
        Label paidLabel = new Label("Paid");
        Label searchNotFound = new Label();
        searchNotFound.setTextFill(Color.web("#FF0000"));

        //TEXTFIELDS
        TextField searchField = new TextField();
        searchField.setPromptText("type booking phone number");
        searchField.setMaxWidth(200);
        searchField.setMinWidth(200);
        ComboBox<String> dateField = new ComboBox<>();
        dateField.setMaxWidth(200);
        dateField.setMinWidth(200);
        ComboBox<String> timeField = new ComboBox<>();
        timeField.setMaxWidth(200);
        TextField seatsField = new TextField();
        seatsField.setPromptText("#");
        seatsField.setMaxWidth(40);
        TextField phoneNrField = new TextField();
        phoneNrField.setPromptText("type customer phone number");
        phoneNrField.setMaxWidth(200);


        //COMBOBOXES
        ObservableList<String> options = addBookingViewController.getMoviesWithSchedule();
        ComboBox titleCombo = new ComboBox(options);
        titleCombo.setPromptText("Choose a movie");


        ComboBox hallCombo = new ComboBox();
        hallCombo.setPromptText("Choose a hall");

        //CHECHBOXES
        CheckBox paidCheck = new CheckBox();
        CheckBox reservedCheck = new CheckBox();

        //BUTTONS

        Button searchButton = new Button();
        Button updateButton = new Button("Update");
        updateButton.setId("back");
        updateButton.setVisible(false);



        Image imageSearch = new Image(getClass().getResourceAsStream("picturesForSchedule/  search.png"));
        searchButton.setGraphic(new ImageView(imageSearch));
        Button addButton = new Button("Add");
        addButton.setId("back");
        addButton.setTextAlignment(TextAlignment.CENTER);
        Button btnBack = new Button("Back");
        btnBack.setId("back");
        btnBack.setTextAlignment(TextAlignment.CENTER);


        //LAYOUT
        BorderPane menu = new BorderPane();
        menu.setId("backgroundImage");

        GridPane elements = new GridPane();
        elements.addRow(0, titleLabel, titleCombo);
        elements.addRow(1, dateLabel, dateField);
        elements.addRow(2, timeLabel, timeField);
        elements.addRow(3, seatsAmountLabel, seatsField);
        elements.addRow(4 );
        elements.addRow(5, paidLabel, paidCheck);
        elements.addRow(7, searchLabel, searchField);
        elements.addRow(8, searchNotFound, searchButton);
        elements.setVgap(5);
        elements.setHgap(20);
        elements.setId("booking");


        FlowPane menubuttons = new FlowPane();
        menubuttons.setHgap(50);
        menubuttons.getChildren().addAll(addButton,updateButton, btnBack);
        menubuttons.setAlignment(Pos.CENTER);
        menubuttons.setPadding(new Insets(0, 0, 15, 0));
        FlowPane title = new FlowPane();
        title.getChildren().addAll(mainLabel);
        title.setPadding(new Insets(25, 0, 0, 0));
        title.setAlignment(Pos.CENTER);


        menu.setTop(title);
        menu.setCenter(elements);
        menu.setBottom(menubuttons);






        //SCENE
        //SCENE
        Scene scene = new Scene(menu, 660, 554);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.show();

        titleCombo.getSelectionModel().selectedItemProperty().addListener(observable -> {

            schedule = addBookingViewController.getSchedule(titleCombo.getSelectionModel().getSelectedItem().toString());
            dateField.getSelectionModel().clearSelection();
            dateField.setItems(addBookingViewController.getDateFieldInfo(titleCombo.getSelectionModel().getSelectedItem().toString()));
            dateField.setPromptText("Choose a week and date");


        });

        dateField.getSelectionModel().selectedItemProperty().addListener(observable -> {
            try {
                int day = Integer.parseInt(dateField.getSelectionModel().getSelectedItem().toString().substring(4, 5));
                int week = Integer.parseInt(dateField.getSelectionModel().getSelectedItem().toString().substring(11));

                ArrayList<String> times = new ArrayList<>();
                for (int i = 0; i < schedule.getSchedule().get(week - 1).get(Integer.toString(day)).size(); i++) {
                    times.add(schedule.getSchedule().get(week - 1).get(Integer.toString(day)).get(i));
                }

                timeField.getSelectionModel().clearSelection();
                timeField.setItems(addBookingViewController.parseTimes(addBookingViewController.getTimes(Integer.parseInt(dateField.getSelectionModel().getSelectedItem().toString().substring(4, 5)),

                        Integer.parseInt(dateField.getSelectionModel().getSelectedItem().toString().substring(11)), schedule)));
                timeField.setPromptText("Choose time");
            } catch (Exception e) {
            }
        });

        searchButton.setOnAction(event -> {
            Booking booking = addBookingViewController.getBookingByPhoneNUmber(searchField.getText());

            if (booking != null) {
                titleCombo.setValue((booking.getTitle()));
                dateField.setValue(booking.getDate());
                timeField.setValue(booking.getTime());
                seatsField.setText(Integer.toString(booking.getSeats()));
                phoneNrField.setText(booking.getPhoneNumber());
                paidCheck.setSelected(booking.isPaid());
                addButton.setVisible(false);
                phoneNrField.setDisable(true);
                updateButton.setVisible(true);
                searchNotFound.setText(null);


            } else {
                searchNotFound.setText("This ticket id doesn't exist");

                dateField.setValue(null);
                timeField.setValue(null);
                seatsField.setText(null);
                phoneNrField.setText(null);
                paidCheck.setSelected(false);
                updateButton.setVisible(false);
                addButton.setVisible(true);

            }


        });

        addButton.setOnAction(event -> {

            if ((seatsField.getText() == null) || (dateField.getValue() == null) || (timeField.getValue() == null) || (titleCombo.getValue() == null)) {
                searchNotFound.setText("You have to add all fields");
            }else{

                try {

                    addBookingViewController.insertBooking(dateField.getValue(), timeField.getValue(), titleCombo.getValue().toString(), Integer.parseInt(seatsField.getText()), phoneNrField.getText(), paidCheck.isSelected());
                    Alert alert = null;

                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Reservation number");
                    alert.setHeaderText("Reservation Number is " + addBookingViewController.getId());

                    Optional<ButtonType> results = alert.showAndWait();
                    if (results.get() == ButtonType.OK) {
                        alert.close();
                        MenuView mainMenu = new MenuView();
                        mainMenu.start();
                    }
                    primaryStage.close();


                    BuyFoodView buyFoodView = new BuyFoodView();
                    buyFoodView.startBuyFoodView();

                    buyFoodView.phoneNumber = addBookingViewController.getId();

                    primaryStage.close();
                } catch (NumberFormatException e) {

                    searchNotFound.setText("You have to give a number for the seat");
                    System.out.println("you can not put letts");
                } catch (NullPointerException e) {
                    System.out.println();
                }
            }

        });

        updateButton.setOnAction(event -> {
            Booking booking = addBookingViewController.getBookingByPhoneNUmber(phoneNrField.getText());
            if (!paidCheck.isSelected() == (booking.isPaid())) {
                addBookingViewController.updatePaid(paidCheck.isSelected(), phoneNrField.getText());
            }
            if (!seatsField.getText().equals(booking.getSeats())) {
                addBookingViewController.updateSeat(seatsField.getText(), phoneNrField.getText());
            }
            if (!timeField.getValue().equals(booking.getTime())) {
                addBookingViewController.updateTime(timeField.getValue(), phoneNrField.getText());

            }
            if (!dateField.getValue().equals(booking.getDate())) {
                addBookingViewController.updateDate(dateField.getValue(), phoneNrField.getText());
            }
            if (!titleCombo.getValue().equals(booking.getTitle())) {
                addBookingViewController.updateTitle(titleCombo.getValue().toString(), phoneNrField.getText());

            }



            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Save");
            alert.setHeaderText("You updated the booking");
            ButtonType buttonTypeYes = new ButtonType("OK");

            Optional<ButtonType> results = alert.showAndWait();
            if (results.get() == ButtonType.OK) {

                MenuView mainMenu = new MenuView();
                mainMenu.start();
                alert.close();
            }


            alert.getButtonTypes().setAll(buttonTypeYes);
            primaryStage.close();
            BuyFoodView buyFoodView = new BuyFoodView();
            buyFoodView.startBuyFoodView();
            buyFoodView.phoneNumber = addBookingViewController.getId();
            Snacks snacks = addBookingViewController.getSnacks(phoneNrField.getText());
                buyFoodView.lSodaAmount.setText(Integer.toString(snacks.getLargeSoda()));
                buyFoodView.sSodaAmount.setText(Integer.toString(snacks.getSmallSoda()));
                buyFoodView.sCandyAmount.setText(Integer.toString(snacks.getSmallCandy()));
                buyFoodView.lCandyAmount.setText(Integer.toString(snacks.getLargeCandy()));
                buyFoodView.lSodaCount = snacks.getLargeSoda();
                buyFoodView.sSodaCount = snacks.getSmallSoda();
                buyFoodView.lCandyCount = snacks.getLargeCandy();
                buyFoodView.sCandyCount = snacks.getSmallCandy();
            buyFoodView.hboxButtons.getChildren().removeAll(buyFoodView.yes, buyFoodView.noThankYou);
            buyFoodView.hboxButtons.getChildren().add(buyFoodView.buttonUpdate);
        });

        btnBack.setOnAction(event1 -> {

            MenuView mainMenu = new MenuView();
            primaryStage.close();
            mainMenu.start();
        });



    }



}