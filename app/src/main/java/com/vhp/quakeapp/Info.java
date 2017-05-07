package com.vhp.quakeapp;

/**
 * Created by root on 15/4/17.
 */

public class Info {

    private double magnitude;
    private String location;
    private long date;
    private String url;

    /**
     * construcutor to get all data
     * @param magnitudeParam
     * @param locationParam
     * @param dateParam
     */
    public Info(double magnitudeParam, String locationParam, long dateParam , String urlParam) {
        this.magnitude = magnitudeParam;
        this.location = locationParam;
        this.date = dateParam;
        url = urlParam;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}
