package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // get the Earthquake object located at the position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the textView in the list_item.xml layout with the ID quake_magnitude
        TextView earthquakeMagnitudeTextView = (TextView) listItemView.findViewById(R.id.quake_magnitude);
        earthquakeMagnitudeTextView.setText(currentEarthquake.getMagnitude());

        // Find the textView in the list_item.xml layout with the ID quake_proximity
        TextView earthquakeProximityTextView = (TextView) listItemView.findViewById(R.id.quake_proximity);
        // Find the textView in the list_item.xml layout with the ID quake_location
        TextView earthquakeLocationTextView = (TextView) listItemView.findViewById(R.id.quake_location);

        String location = currentEarthquake.getLocation();

        if (location.contains("of")) {
            int index = location.indexOf("of");
            String proximity = location.substring(0, index + 2);
            String earthquakeLoc = location.substring(index + 3, location.length());
            earthquakeProximityTextView.setText(proximity);
            earthquakeLocationTextView.setText(earthquakeLoc);
        } else {
            earthquakeProximityTextView.setText(R.string.near_the);
            earthquakeLocationTextView.setText(location);
        }

        // Find the textView in the list_item.xml layout with the ID quake_date
        TextView earthquakeDateTextView = (TextView) listItemView.findViewById(R.id.quake_date);
        earthquakeDateTextView.setText(currentEarthquake.getDate());

        // Find the textView in the list_item.xml layout with the ID quake_date
        TextView earthquakeTimeTextView = (TextView) listItemView.findViewById(R.id.quake_time);
        earthquakeTimeTextView.setText(currentEarthquake.getTime());

        return listItemView;
    }
}
