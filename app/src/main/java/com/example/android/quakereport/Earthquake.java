package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {
    private String mMagnitude;
    private String mLocation;
    private Date mDate;

    public Earthquake(String magnitude, String location, Date date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public Date getDate() {
        return mDate;
    }
}