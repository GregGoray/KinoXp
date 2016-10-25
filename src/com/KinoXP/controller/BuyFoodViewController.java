package com.KinoXP.controller;

import com.KinoXP.model.BuyFoodViewModel;

/**
 * Created by MazurJestBoski on 2016-03-05.
 */
public class BuyFoodViewController {
    BuyFoodViewModel buyFoodViewModel = new BuyFoodViewModel();

    public void insertFood(int phonenumber, int sCandyCount, int lCandyCount, int sSodaCount, int lSodaCount){

        buyFoodViewModel.setFoodToDB(phonenumber ,sCandyCount,lCandyCount,sSodaCount, lSodaCount);
    }

    public void updateLargeCandy(int lCandyCount, int phonenumber){

        buyFoodViewModel.updateLargeCandy(lCandyCount, phonenumber);
    }

    public void updateSmallCandy(int sCandyCount, int phonenumber){

        buyFoodViewModel.updateSmallCandy(sCandyCount, phonenumber);
    }

    public void updateLargeSoda(int lSodaCount, int phonenumber){

        buyFoodViewModel.updateLargeSoda(lSodaCount, phonenumber);
    }

    public void updateSmallSoda(int sSodaCount, int phonenumber){

        buyFoodViewModel.updateSmallSoda(sSodaCount, phonenumber);
    }
}
