package com.example.android.quakereport;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {
    private String mMagnitude;
    private String mLocation;
    private long mDate;

    public Earthquake(String magnitude, String location, long date) {
        mMagnitude = magnitude;
        mLocation = location;
        mDate = date;
    }

    public String getMagnitude() {
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
}