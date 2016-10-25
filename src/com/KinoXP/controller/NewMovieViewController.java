package com.KinoXP.controller;

import com.KinoXP.model.NewMovieViewModel;
import com.KinoXP.view.NewMovieView;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by quena on 25-02-2016.
 */
public class NewMovieViewController {

    NewMovieViewModel newMovieViewModel = new NewMovieViewModel();


        public void newMovieViewDisplay(){
            NewMovieView newMovieView = new NewMovieView();
        newMovieView.start();
    }

    public ImageView getWrapImageFromUrlCtrl(String url) {
        ImageView imageView = null;
        imageView = newMovieViewModel.wrapImageFromUrl(url);
        return imageView;
    }

    public ArrayList<String> getUrlListCtr() {
        return newMovieViewModel.getUrlListFromDB();
    }

    public ArrayList<String> getTitleListCtr() {
        return newMovieViewModel.getTitleListFromDB();
    }



}
