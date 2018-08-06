package com.example.android.quakereport;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    private Double mMagnitude;
    private String mLocation;
    private long mDate;
    private String mUrl;

    public Earthquake(Double magnitude, String location, long date, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
        mUrl = url;
    }

    public Double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDate() {
        long timeInMilliseconds = mDate;
        Date dateObject = new Date(timeInMilliseconds);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        return dateFormatter.format(dateObject);
    }

    public String getTime() {
        long timeInMilliseconds = mDate;
        Date dateObject = new Date(timeInMilliseconds);
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        return timeFormatter.format(dateObject);
    }

    public String getUrl() {
        return mUrl;
    }
}