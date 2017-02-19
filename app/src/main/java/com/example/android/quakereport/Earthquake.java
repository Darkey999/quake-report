package com.example.android.quakereport;

/**
 * Created by Gosu on 2017-02-19.
 */
public class Earthquake {
    private double mag;
    private String place, date;

    //constructor getting magnitude, place and date of the earthquake
    public Earthquake(double mag, String place, String date) {
        this.mag = mag;
        this.place = place;
        this.date = date;
    }

    public double returnMag() {
        return mag;
    }

    public String returnPlace() {
        return place;
    }

    public String returnDate() {
        return date;
    }
}
