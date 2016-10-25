package com.KinoXP.view;

import com.KinoXP.controller.BuyFoodViewController;
import com.KinoXP.model.Booking;
import com.KinoXP.model.Snacks;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 * Created by hartyandi on 3/4/16.
 */
public class BuyFoodView {

    BuyFoodViewController buyFoodViewController = new BuyFoodViewController();
    MenuView menuView;
    Label lSodaAmount;
    Label lCandyAmount;
    Label sSodaAmount;
    Label sCandyAmount;


    int sSodaCount = 0;
    int lSodaCount = 0;
    int sCandyCount = 0;
    int lCandyCount = 0;
    int phoneNumber;
    Stage primaryStage;

    HBox hboxButtons;
    Button buttonUpdate;
    Button yes;
    Button noThankYou;




    public void startBuyFoodView(){

        Label mainLabel = new Label("Do you want any goods?");
        Label largeSoda = new Label("Large Soda");
        Label smallSoda = new Label("Small Soda");
        Label largeCandy = new Label("Large Candy");
        Label smallCandy = new Label("Small Candy");

        Button largeSodaPlus = new Button("+");
        Button largeSodaMinus = new Button("-");
        Button smallSodaPlus = new Button("+");
        Button smallSodaMinus = new Button("-");
        Button smallCandyPlus = new Button("+");
        Button smallCandyMinus = new Button("-");
        Button largeCandyPlus = new Button("+");
        Button largeCandyMinus = new Button("-");
        buttonUpdate = new Button("Update");
        noThankYou = new Button("No");
        yes = new Button("Yes");

        lSodaAmount = new Label("?");
        lCandyAmount = new Label("?");
        sSodaAmount = new Label("?");
        sCandyAmount = new Label("?");
        lCandyAmount.setAlignment(Pos.CENTER);
        sCandyAmount.setAlignment(Pos.CENTER);
        lSodaAmount.setAlignment(Pos.CENTER);
        sSodaAmount.setAlignment(Pos.CENTER);
        lSodaAmount.setTextAlignment(TextAlignment.CENTER);
        sSodaAmount.setTextAlignment(TextAlignment.CENTER);
        lCandyAmount.setTextAlignment(TextAlignment.CENTER);
        sCandyAmount.setTextAlignment(TextAlignment.CENTER);
        sSodaAmount.setMinWidth(20);
        lSodaAmount.setMinWidth(20);
        lCandyAmount.setMinWidth(20);
        sCandyAmount.setMinWidth(20);
        VBox layout = new VBox();

        HBox hboxLSoda = new HBox();
        hboxLSoda.getChildren().addAll(largeSodaMinus, lSodaAmount, largeSodaPlus);
        hboxLSoda.setAlignment(Pos.CENTER);
        HBox hboxSSoda = new HBox();
        hboxSSoda.getChildren().addAll(smallSodaMinus, sSodaAmount, smallSodaPlus);
        hboxSSoda.setAlignment(Pos.CENTER);
        HBox hboxLCandy = new HBox();
        hboxLCandy.getChildren().addAll(largeCandyMinus, lCandyAmount, largeCandyPlus);
        hboxLCandy.setAlignment(Pos.CENTER);
        HBox hboxSCandy = new HBox();
        hboxSCandy.getChildren().addAll(smallCandyMinus, sCandyAmount, smallCandyPlus);
        hboxSCandy.setAlignment(Pos.CENTER);
        hboxButtons = new HBox();
        hboxButtons.setPadding(new Insets(10, 10, 10, 10));
        hboxButtons.setAlignment(Pos.CENTER);

        //*****************************************
        // BUTTONS ON ACTION

        largeSodaPlus.setOnAction(event ->{
            lSodaCount++;
            lSodaAmount.setText(Integer.toString(lSodaCount));
        });

        largeSodaMinus.setOnAction(event ->{
            if(lSodaCount>0)
            {lSodaCount--;}
            lSodaAmount.setText(Integer.toString(lSodaCount));
        });

        smallSodaPlus.setOnAction(event ->{
            sSodaCount++;
            sSodaAmount.setText(Integer.toString(sSodaCount));
        });

        smallSodaMinus.setOnAction(event ->{
            if(sSodaCount>0)
            {sSodaCount--;}
            sSodaAmount.setText(Integer.toString(sSodaCount));
        });

        largeCandyPlus.setOnAction(event ->{
            lCandyCount++;
            lCandyAmount.setText(Integer.toString(lCandyCount));
        });

        largeCandyMinus.setOnAction(event ->{
            if(lCandyCount>0)
            {lCandyCount--;}
            lCandyAmount.setText(Integer.toString(lCandyCount));
        });

        smallCandyPlus.setOnAction(event ->{
            sCandyCount++;
            sCandyAmount.setText(Integer.toString(sCandyCount));
        });

        smallCandyMinus.setOnAction(event ->{
            if(sCandyCount>0)
            {sCandyCount--;}
            sCandyAmount.setText(Integer.toString(sCandyCount));
        });

        //**********************
        //      YES NO ACTIONS

        yes.setOnAction(event -> {
            buyFoodViewController.insertFood(phoneNumber, sCandyCount, lCandyCount, sSodaCount, lSodaCount);
            menuView = new MenuView();
            menuView.start();
            primaryStage.close();
        });

        noThankYou.setOnAction(event ->  {
            buyFoodViewController.insertFood(phoneNumber,0,0,0,0);
            menuView = new MenuView();
            menuView.start();
            primaryStage.close();
        });

        Snacks snacks = new Snacks();

        buttonUpdate.setOnAction(event -> {
            if (sCandyCount != snacks.getSmallCandy()) {
                buyFoodViewController.updateSmallCandy(sCandyCount, phoneNumber);
            }

            if (lCandyCount != snacks.getLargeCandy()) {
                buyFoodViewController.updateLargeCandy(lCandyCount, phoneNumber);
            }

            if (sSodaCount != snacks.getSmallSoda()) {
                buyFoodViewController.updateSmallSoda(sSodaCount, phoneNumber);
            }

            if (lSodaCount != snacks.getLargeSoda()) {
                buyFoodViewController.updateLargeSoda(lSodaCount, phoneNumber);
            }
            primaryStage.close();
        })
           ;





        hboxButtons.getChildren().addAll(noThankYou,yes);
        layout.getChildren().addAll(mainLabel, smallSoda, hboxSSoda, largeSoda, hboxLSoda, smallCandy, hboxSCandy, largeCandy, hboxLCandy, hboxButtons);
        layout.setPadding(new Insets(40, 40, 40, 60));
        layout.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(layout, 250, 310);
        primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
