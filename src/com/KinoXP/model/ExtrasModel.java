package com.KinoXP.model;


/**
 * Created by Monica on 06-03-2016.
 */
public class ExtrasModel {
    private String category;
    private int largePrice;
    private int smallPrice;

    //CONSTRUCTOR
    public ExtrasModel(String category, int largePrice, int smallPrice) {
        this.category = category;
        this.largePrice = largePrice;
        this.smallPrice = smallPrice;
    }
    //GETTERS AND SETTERS
    public int getSmallPrice() {
        return smallPrice;
    }
    public void setSmallPrice(int smallPrice) {
        this.smallPrice = smallPrice;
    }
    public int getLargePrice() {
        return largePrice;
    }
    public void setLargePrice(int largePrice) {
        this.largePrice = largePrice;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
