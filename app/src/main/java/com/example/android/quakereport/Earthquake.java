package com.example.android.quakereport;

/**
 * Created by Gosu on 2017-02-19.
 */
public class Earthquake {
    private double mag;
    private String place, date, time, url;

    //constructor getting magnitude, place and date of the earthquake
    public Earthquake(double mag, String place, String date, String time, String url) {
        this.mag = mag;
        this.place = place;
        this.date = date;
        this.time = time;
        this.url = url;
    }

    public double returnMag() {
        return mag;
    }

    public String returnPlace() {
        return place;
    }

    public String returnTime() {
        return time;
    }

    public String returnUrl() {
        return url;
    }

    public String returnDate() {
        return date;
    }
}
