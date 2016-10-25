package com.KinoXP.view;

import com.KinoXP.controller.AddMovieFormViewController;
import com.KinoXP.controller.ManageMovieScheduleController;
import com.KinoXP.model.AddMovieFormViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Grzegorz, Mazur on 25-02-2016.
 */
public class AddMovieFormView {

    private AddMovieFormViewController addMovieFormViewController = new AddMovieFormViewController();
    private AddMovieFormViewModel addMovieFormViewModel = new AddMovieFormViewModel();
    private Stage stage;
    TextField posterPathTxt;
    TextField titleTxt;
    //CONSTRUCTOR
    public AddMovieFormView() {
        addMovieFormViewModel = new AddMovieFormViewModel();
        addMovieFormViewController = new AddMovieFormViewController(this, addMovieFormViewModel);
    }

    public void getAddMovieView() {

        //TEXT FIELDS
         titleTxt = new TextField();
        TextField playingTimeInMinutesTxt = new TextField();
        TextField releaseYearTxt = new TextField();
        TextField directorTxt = new TextField();
         posterPathTxt = new TextField();
        //TextField movieTheaterTxt = new TextField();

        TextField genreTxt = new TextField();
        TextField ageLimitTxt = new TextField();

        //TEXT AREAS
        TextArea plotTxt = new TextArea();
        TextArea cast = new TextArea();

        //LABELS
        Label mainTitleLbl = new Label("Add Movie");
        mainTitleLbl.setFont(Font.font("Courier", FontWeight.BOLD, 25));
        mainTitleLbl.setFont(Font.font(25));
        Label titleLbl = new Label("Title");
        Label yearLbl = new Label("Year");
        Label durationLbl = new Label("Duration in minutes!");
        Label genreLbl = new Label("Genre");
        Label descriptionLbl = new Label("Description");
        Label directorLbl = new Label("Director");
        Label ageLimitLbl = new Label("Age Limit");
        Label castLbl = new Label("Cast");
        Label posterLbl = new Label("Poster URL");
        Label movieTheaterLbl = new Label("Movie Theater");
        Label movieTime = new Label("Movie premiere: ");

        //BUTTONS

        Button addMovieBtn = new Button("Add Movie");
        addMovieBtn.setId("back");
        Button backBtn = new Button("Back");
        backBtn.setId("back");

        //calendar

        DatePicker datePicker = new DatePicker();
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Theater 1",
                        "Theater 2"
                );

        ComboBox<String>movieTheaterTxt= new ComboBox<>(options);

        //active validation of  fields (decoration)
        addMovieFormViewController.validateFieldsControlsFx(titleTxt, playingTimeInMinutesTxt, releaseYearTxt,
                plotTxt, directorTxt, posterPathTxt, cast,movieTheaterTxt, genreTxt, ageLimitTxt);

        // Add Movie Button Action
        addMovieBtn.setOnAction(event -> {
            //getting date from datePicer
            try{
                LocalDate localDate = datePicker.getValue();
                java.sql.Date date = java.sql.Date.valueOf(localDate);
              //  Calendar cal = Calendar.getInstance();
               // cal.setTime(date);
               // int week = cal.get(Calendar.WEEK_OF_YEAR);
               // System.out.println("week nr is: " + week);

                addMovieFormViewController.validateFieldsAndAction(titleTxt, playingTimeInMinutesTxt, releaseYearTxt,
                        plotTxt, directorTxt, posterPathTxt, cast,movieTheaterTxt.getValue(), genreTxt, ageLimitTxt,date);
            } catch (NullPointerException e) {

                System.out.println("Please choose date from my sweet datePicker");

            }
            //converting date form dp to date format readable by db
            //checking if  you choose date
                //parse through counstructor




        });



        // ACTION - BACK BUTTON
        backBtn.setOnAction(event -> {
            closeStage();
            NewMovieView newMovieView = new NewMovieView();
            newMovieView.start();
        });


        //H-BOXES
        HBox hBoxTitle = new HBox();
        hBoxTitle.getChildren().add(mainTitleLbl);
        hBoxTitle.setAlignment(Pos.TOP_LEFT);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(durationLbl, genreLbl, ageLimitLbl);
        hBox1.setSpacing(120);

        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(playingTimeInMinutesTxt, genreTxt, ageLimitTxt);
        hBox2.setSpacing(30);

        HBox hBox3 = new HBox();
        hBox3.getChildren().addAll(directorLbl, yearLbl);
        hBox3.setSpacing(200);

        HBox hBox4 = new HBox();
        hBox4.getChildren().addAll(directorTxt, releaseYearTxt);
        hBox4.setSpacing(30);

        HBox hBox5 = new HBox();
        hBox5.getChildren().addAll(cast);
        hBox5.setSpacing(30);

        HBox hBox6 = new HBox();
        hBox6.getChildren().addAll(posterPathTxt);
        hBox6.setSpacing(30);

        HBox hBox7 = new HBox();
        hBox7.getChildren().addAll(addMovieBtn, backBtn);
        hBox7.setSpacing(30);
        hBox7.setAlignment(Pos.BOTTOM_RIGHT);

        //V-BOX CONTAINING THE H-BOXES ABOVE
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBoxTitle, titleLbl, titleTxt, descriptionLbl, plotTxt,movieTime, datePicker, hBox1, hBox2, hBox3,
                            hBox4, castLbl, hBox5, posterLbl, hBox6, movieTheaterLbl, movieTheaterTxt, hBox7);
        vBox.setPadding(new Insets(40, 40, 40, 60));
        vBox.setSpacing(10);

        //LAYOUT OF THE SCENE
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(25));
        vBox.setId("adding");
        borderPane.setLeft(vBox);
        borderPane.setId("seamlessBackgroundImage");

        //SETTING UP THE SCENE AND THE STAGE
        Scene scene = new Scene(borderPane, 650, 700);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

    //METHOD FOR THE ALERT MESSAGES SHOWN TO THE USER
    public void updateAlertMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }



    //METHOD TO USE FOR CLOSING THE STAGE
    public void closeStage(){
        stage.close();
    }

 //get url form addmovie
 public String getUrl(){
     return  posterPathTxt.getText();
 }
    public String getTitle(){
        return titleTxt.getText();
    }

}
