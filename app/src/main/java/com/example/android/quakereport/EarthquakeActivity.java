package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {
    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create ArrayList of earthquake objects
        final ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
        earthquakes.add(new Earthquake("6.0", "San Francisco", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "London", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "Tokyo", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "Mexico City", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "Moscow", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "Rio de Janeiro", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "Paris", "8:30AM"));

        EarthquakeAdapter itemsAdapter = new EarthquakeAdapter(this, earthquakes);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        earthquakeListView.setAdapter(itemsAdapter);
    }
}
