package com.KinoXP.view;

import com.KinoXP.controller.ManageMovieScheduleController;
import com.KinoXP.model.Movie;
import com.KinoXP.model.TimeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * Created by krystian on 2015-09-04.
 */
public class Schedule {
     //BUTTONS//
    //button for change week
    Button nextButton;
    //button for change week
    Button preButton;
    //save Button
    Button saveButton;
    //back Button
    Button back;
    //label for week nr
    //LABELS//
    Label nrOfWeek;
    //label informing about choosing movie firstly
    Label pleaseSelectMovie;
    //label for storing movie title
    Label movieTitleText;
    //table view for holding schedule
    TableView<TimeModel> table;
    //controller object
    ManageMovieScheduleController manageMovieScheduleController  = new ManageMovieScheduleController();
    //some layouts
    VBox vBox;
    VBox vBox1;
    //movie object
    Movie movie;
    Stage primaryStage;

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public Schedule(Movie m){

        movie= m;
    }
    //empty construction for schedue
    public Schedule(){

    }

    public void start() {

        manageMovieScheduleController = new ManageMovieScheduleController(this,movie);
        // set primaryStage
         primaryStage = new Stage();
        //iniciate table
        table = manageMovieScheduleController.getTable();
        //nr of week
        nrOfWeek = new Label("Week "+manageMovieScheduleController.getWeekFromDb());
        nrOfWeek.setId("room");
        //back Button
        back = new Button("Go back");

        back.setId("back");
        //acton for back
        back.setOnAction(event3 -> {
            //call controller to open menuView
            manageMovieScheduleController.startMenuView();

        });
        //movie title lablle
        Label movieTitle = new Label("movie 1");
        //movieTitle
        movieTitleText = new Label("Movie title: " );
        movieTitleText.setId("movieTitleText");

        pleaseSelectMovie = new Label("Please select movie");
        pleaseSelectMovie.setPrefSize(200,30);
        javafx.scene.image.Image image = new javafx.scene.image.Image(getClass().getResource("picturesForSchedule/arrowRight.png").toExternalForm());
        nextButton = new Button();
        nextButton.setGraphic(new ImageView(image));

        nextButton.setId("navigationButon");
        nextButton.setOnMouseClicked(event2 -> {

            manageMovieScheduleController.nextButtonAction();

        });
        javafx.scene.image.Image image1 = new javafx.scene.image.Image(getClass().getResource("picturesForSchedule/arrowLefi.png").toExternalForm());

        preButton = new Button();
        preButton.setGraphic(new ImageView(image1));
        preButton.setId("navigationButon");
        preButton.setOnMouseClicked(event2 -> {

            manageMovieScheduleController.preButtonAction();

        });

        saveButton = new Button("Save ");
        saveButton.setId("back");
        saveButton.setOnAction(event1 -> {

            manageMovieScheduleController.saveSchedule();
            conformitionForSaving();

        });
        vBox = new VBox();
        vBox.setId("vbox");
        vBox1 = new VBox();
        HBox hBox = new HBox(265);
        HBox hBox1 = new HBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        primaryStage.setTitle("Schedule");


        manageMovieScheduleController.setItems();


        hBox.setMargin(preButton, new Insets(20,0,0,0));
        hBox.setMargin(nrOfWeek, new Insets(40,0,0,0));
        hBox.setMargin(nextButton, new Insets(20,0,0, 0));
        hBox.getChildren().addAll(preButton,nrOfWeek,nextButton);
        hBox1.setMargin(back, new Insets(10,0,0,10));
        hBox1.setMargin(saveButton, new Insets(10,0,0,480));
        hBox1.getChildren().addAll(back,saveButton);
        vBox.setMargin(movieTitleText, new Insets(20,0,0,0));
        movieTitle.setText(movie.getTitle());
        vBox.getChildren().addAll(hBox,movieTitleText,movieTitle,table,hBox1);



        Scene scene = new Scene(vBox, 700, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        movieTitle.setId("movieTitle");

        primaryStage.setScene(scene);
        primaryStage.show();


    }



    public Label getNrOfWeek(){
        return  nrOfWeek;
    }
    public void startSchedule(){


        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Theater 1",
                        "Theater 2"

                );


        Stage primaryStage = new Stage();
        VBox vbox = new VBox();
        vbox.setId("vbox");
        Scene scene = new Scene(vbox,650,600);
        HBox hBox = new HBox(30);

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        Label label = new Label("Welcome to manage schedule");
        label.setId("welcome");
        Label label1 = new Label("Please choose room");
        label1.setId("room");
        Label label2 = new Label("Please select movie");
        label2.setId("room");
        Button button = new Button("Go to schedule");
        button.setId("button");
        Button button1 = new Button("Go to main manu");
        button1.setId("button");
        button1.setOnAction(event2 -> {


            MenuView mainMenu = new MenuView();
            primaryStage.close();
            mainMenu.start();
        });
        button1.setId("button");
        hBox.getChildren().addAll(button1,button);

        ComboBox<Movie> comboBox = new ComboBox<>();

        ComboBox<String>movieTheaterTxt= new ComboBox<>(options);
        movieTheaterTxt.setOnAction(event -> {
            comboBox.setItems(manageMovieScheduleController.getMovieTitles(movieTheaterTxt.getSelectionModel().getSelectedItem()));
        });
        movieTheaterTxt.setValue("Theater 1");

        String cinameRoomName = movieTheaterTxt.getSelectionModel().getSelectedItem();
        comboBox.setItems(manageMovieScheduleController.getMovieTitles(cinameRoomName));

        vbox.getChildren().addAll(label,label1,movieTheaterTxt, label2,comboBox,hBox);
        vbox.setMargin(label,new Insets(120,0,0,0));
        vbox.setMargin(label1,new Insets(30,0,0,0));
        vbox.setMargin(movieTheaterTxt,new Insets(10,0,0,0));
        vbox.setMargin(label2,new Insets(30,0,0,0));
        vbox.setMargin(comboBox,new Insets(10,0,0,0));
        vbox.setMargin(hBox,new Insets(180,0,0,0));


        button.setOnAction(event1 -> {
                 movie =   comboBox.getSelectionModel().getSelectedItem();

                manageMovieScheduleController.scheduleDisplay(movie);

                label2.setId("chooseMovie");


        });

        vbox.setAlignment(Pos.TOP_CENTER);
        hBox.setAlignment(Pos.TOP_CENTER);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //conformition for saving

    public void conformitionForSaving(){
        VBox vbox = new VBox();

        Scene scene = new Scene(vbox,650,600);
        Stage primaryStage = new Stage();


        String title = movie.getTitle();
        Label label = new Label("You have save "+" week "+122 +"\n"+" for \n "+ "''"+title+"''");
        label.setId("conformition");
        Button button = new Button("ok");
        button.setId("back");

        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(label,button);
        vbox.setMargin(label, new Insets(30,0,0,0));
        vbox.setMargin(button, new Insets(30,0,30,0));
        vbox.setId("vbox");

        button.setId("ok");

        button.setOnAction(event -> {
            primaryStage.close();
        });
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public TableView getTable(){
        return table;
    }

}
