package com.KinoXP.model;

/**
 * Created by MazurJestBoski on 2016-03-09.
 */
public class Snacks {
    private int smallCandy, largeCandy, smallSoda, largeSoda;


    public int getSmallCandy() {
        return smallCandy;
    }

    public int getLargeCandy() {
        return largeCandy;
    }

    public int getSmallSoda() {
        return smallSoda;
    }

    public int getLargeSoda() {
        return largeSoda;
    }

    public Snacks(int smallCandy, int largeCandy, int smallSoda, int largeSoda) {
        this.smallCandy = smallCandy;
        this.largeCandy = largeCandy;
        this.smallSoda = smallSoda;
        this.largeSoda = largeSoda;
    }
    public Snacks()
    {
    }

}
