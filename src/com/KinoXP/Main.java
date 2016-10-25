package com.KinoXP;
import com.KinoXP.controller.LoginViewController;
import com.KinoXP.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{

    LoginView loginView = new LoginView();
    LoginViewController loginViewController = new LoginViewController();


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loginViewController.checkForInternetConnection();
    }
}