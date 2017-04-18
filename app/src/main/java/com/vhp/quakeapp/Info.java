package com.vhp.quakeapp;

/**
 * Created by root on 15/4/17.
 */

public class Info {

    private float magnitude;
    private String location;
    private String date;

    /**
     * construcutor to get all data
     * @param magnitudeParam
     * @param locationParam
     * @param dateParam
     */
    public Info(float magnitudeParam, String locationParam, String dateParam) {
        this.magnitude = magnitudeParam;
        this.location = locationParam;
        this.date = dateParam;
    }

    public float getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
