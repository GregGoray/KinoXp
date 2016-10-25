package com.KinoXP.view;

import com.KinoXP.controller.AddMovieFormViewController;
import com.KinoXP.controller.EditMovieViewController;
import com.KinoXP.controller.NewMovieViewController;
import com.KinoXP.model.EditMovieViewModel;
import javafx.application.Application;
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
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.Optional;

/**
 * Created by Paula on 25/2/16.
 */
public class EditMovieView extends Application {

    private EditMovieViewController editMovieViewController;
    private AddMovieFormViewController addMovieFormViewController;
    private Button editMovieBtn;
    private  EditMovieViewModel manageMovieViewModul;
    private Stage primaryStage;
    private String title;
    //CONSTRUCTOR
    public EditMovieView(String title) {
        this.title = title;
    }

    public EditMovieViewController getEditMovieViewController() {
        return editMovieViewController;
    }

    public void setEditMovieViewController(EditMovieViewController editMovieViewController) {
        this.editMovieViewController = editMovieViewController;
    }

    public EditMovieViewModel getManageMovieViewModul() {
        return manageMovieViewModul;
    }

    public void setManageMovieViewModul(EditMovieViewModel manageMovieViewModul) {
        this.manageMovieViewModul = manageMovieViewModul;
    }

    //START METHOD FOR THE VIEW
    public void start(Stage primaryStage) throws Exception {

        VBox vBox = new VBox();

        ResultSet resultSet = manageMovieViewModul.getMovie(title);
        String[] result = new String[12];


        while (resultSet.next()){
            for (int i = 1; i < 12; i++){
            result[i] = resultSet.getString(i);
            }
        }
        //        MovieModel movieModel = new MovieModel(result[2], result[3], result[4], result[5], result[6], result[7], result[8], result[9], result[10]);
        //TEXT FIELDS
        TextField titleTxt = new TextField(result[2]);
        TextField playingTimeInMinutesTxt = new TextField(result[3]);
        TextField releaseYearTxt = new TextField(result[4]);
        TextField directorTxt = new TextField(result[6]);
        TextField posterPathTxt = new TextField(result[7]);
        //TextField movieTheaterTxt = new TextField(result[9]);
        TextField genreTxt = new TextField(result[10]);
        TextField ageLimitTxt = new TextField(result[11]);

        //TEXT AREAS
        TextArea plotTxtArea = new TextArea(result[5]);
        TextArea mainActorTxtArea = new TextArea(result[8]);

        //LABELS
        Label mainTitleLbl = new Label("Edit MovieModel");
        mainTitleLbl.setFont(new Font("Serif", 30));
        Label titleLbl = new Label("Title");
        Label yearLbl = new Label("Year");
        Label durationLbl = new Label("Duration");
        Label genreLbl = new Label("Genre");
        Label descriptionLbl = new Label("Description");
        Label directorLbl = new Label("Director");
        Label ageLimitLbl = new Label("Age Limit");
        Label castLbl = new Label("Cast");
        Label posterLbl = new Label("Poster URL");
        Label movieTheaterLbl = new Label("MovieModel Theater");

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Theater 1",
                        "Theater 2"
                );

        ComboBox<String> movieTheaterCombo = new ComboBox<>(options);
        movieTheaterCombo.setValue(result[9]);

        //BUTTONS
        //Button addCastBtn = new Button("Add");
        Button deleteMovieBtn = new Button("Delete");
        Button editMovieBtn = new Button("Save");
        Button backBtn = new Button("Back");

        //H-BOXES
        HBox hBoxTitle = new HBox();
        hBoxTitle.getChildren().add(mainTitleLbl);
        hBoxTitle.setAlignment(Pos.TOP_LEFT);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(durationLbl, genreLbl, ageLimitLbl);
        hBox1.setSpacing(155);

        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(playingTimeInMinutesTxt, genreTxt, ageLimitTxt);
        hBox2.setSpacing(30);

        HBox hBox3 = new HBox();
        hBox3.getChildren().addAll(directorLbl, yearLbl);
        hBox3.setSpacing(150);

        HBox hBox4 = new HBox();
        hBox4.getChildren().addAll(directorTxt, releaseYearTxt);
        hBox4.setSpacing(30);

        HBox hBox5 = new HBox();
        hBox5.getChildren().addAll(mainActorTxtArea);
        hBox5.setSpacing(30);

        HBox hBox6 = new HBox();
        hBox6.getChildren().addAll(posterPathTxt);
        hBox6.setSpacing(30);

        HBox hBox7 = new HBox();
        hBox7.getChildren().addAll(editMovieBtn,deleteMovieBtn, backBtn);
        hBox7.setSpacing(30);
        hBox7.setAlignment(Pos.BOTTOM_RIGHT);

        //V-BOXES CONTAINING THE H-BOXES ABOVE
        vBox.getChildren().addAll(hBoxTitle, titleLbl, titleTxt, descriptionLbl, plotTxtArea, hBox1,
                                hBox2, hBox3, hBox4, castLbl, hBox5, posterLbl, hBox6, movieTheaterLbl, movieTheaterCombo, hBox7);
        vBox.setPadding(new Insets(40, 40, 40, 60));
        vBox.setSpacing(10);

        //LAYOUT OF THE SCENE
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vBox);

        //SETTING UP THE SCENE AND THE STAGE
        Scene scene = new Scene(borderPane, 700, 650);
        primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();
        this.primaryStage = primaryStage;

        //HANDLING THE BUTTONS ACTIONS
        final Stage finalPrimaryStage = primaryStage;
        deleteMovieBtn.setOnAction(event -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Movie");
            alert.setHeaderText("Are you sure you want to delete the movie?");
            ButtonType buttonTypeYes = new ButtonType("YES");
            ButtonType buttonTypeNo = new ButtonType("NO");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);


            Optional<ButtonType> results = alert.showAndWait();
            if (results.get() == buttonTypeYes){
                editMovieViewController.deleteMovieButtonAction(titleTxt.getText());
                NewMovieViewController newMovieViewController = new NewMovieViewController();
                newMovieViewController.newMovieViewDisplay();
                deleteFile();
                finalPrimaryStage.close();
                closeStage();
            } else {
                alert.close();
            }

        });

        //active validation of  fields (decoration)
        editMovieViewController.validateEditFieldsControlsFx(titleTxt, playingTimeInMinutesTxt, releaseYearTxt,
                plotTxtArea, directorTxt, posterPathTxt, mainActorTxtArea, movieTheaterCombo, genreTxt, ageLimitTxt);

        editMovieBtn.setOnAction(event -> {
            editMovieViewController.editMovieButtonAction(titleTxt, playingTimeInMinutesTxt,
                    releaseYearTxt, plotTxtArea.getText(), directorTxt, posterPathTxt,
                    mainActorTxtArea.getText(), movieTheaterCombo.getValue().toString(), genreTxt, ageLimitTxt, result[2], result[3], result[4], result[5],
                    result[6], result[7], result[8], result[9], result[10], result[11]);
        });

        backBtn.setOnAction(event -> {
            NewMovieViewController newMovieViewController = new NewMovieViewController();
            newMovieViewController.newMovieViewDisplay();
            this.primaryStage.close();
        });


    }

    //METHOD FOR THE ALERT MESSAGES SHOWN TO THE USER
    public void updateAlertMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setContentText(message);
        alert.showAndWait();
    }

    public void closeStage(){
        primaryStage.close();
    }

    public void deleteFile(){
        editMovieViewController.deleteFileAction();
    }
}
