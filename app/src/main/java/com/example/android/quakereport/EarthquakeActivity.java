package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class EarthquakeActivity extends AppCompatActivity {
    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.list, new EarthquakeFragment())
                .commit();
    }
}
