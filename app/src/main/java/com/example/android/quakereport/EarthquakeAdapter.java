package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;
import android.graphics.drawable.GradientDrawable;

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
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(currentEarthquake.getMagnitude());
        earthquakeMagnitudeTextView.setText(output);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) earthquakeMagnitudeTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

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

    private int getMagnitudeColor(double magnitude) {
        // Anything above a magnitude 10 earthquake uses the darkest red color (#C03823).
        // Anything between a magnitude 9 and 10 earthquake uses a slightly lighter red color (#D93218),
        // anything between a magnitude 8 and 9 earthquake uses an even lighter red color (#E13A20),
        // and so on. Each level has a different color. Anything below a 2.0 earthquake uses a blue
        // color (#4A7BA7). The specific hex values are provided below.
        int magnitudeColorInt;
        int magnitudeRoundedDown = (int) Math.floor(magnitude);
        switch (magnitudeRoundedDown) {
            case 0:
            case 1:
                magnitudeColorInt = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2:
                magnitudeColorInt = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3:
                magnitudeColorInt = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4:
                magnitudeColorInt = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5:
                magnitudeColorInt = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6:
                magnitudeColorInt = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7:
                magnitudeColorInt = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8:
                magnitudeColorInt = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9:
                magnitudeColorInt = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magnitudeColorInt = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }
        return magnitudeColorInt;
    }
}
