package com.example.android.quakereport;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EarthquakeFragment extends Fragment {
    public EarthquakeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.earthquake_activity, container, false);

        // Create ArrayList of earthquake objects
        final ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();
        earthquakes.add(new Earthquake("6.0", "San Francisco", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "London", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "Tokyo", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "Mexico City", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "Moscow", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "Rio de Janeiro", "8:30AM"));
        earthquakes.add(new Earthquake("6.0", "Paris", "8:30AM"));

        EarthquakeAdapter itemsAdapter = new EarthquakeAdapter(getActivity(), earthquakes);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) rootView.findViewById(R.id.list);

        earthquakeListView.setAdapter(itemsAdapter);
        return rootView;
    }
}
