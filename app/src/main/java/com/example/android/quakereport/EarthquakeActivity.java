package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {
    private static final String TAG = EarthquakeActivity.class.getSimpleName();

    /** ListView for earthquake dta **/
    private ListView earthquakeListView;

    /** text view to show empty view **/
    private TextView emptyTextView;

    /** Adapter for the list of earthquakes */
    private EarthquakeAdapter itemsAdapter;

    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=20";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        /** check for internet connection **/
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        itemsAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        // Find a reference to the {@link ListView} in the layout
        earthquakeListView = (ListView) findViewById(R.id.list);
        emptyTextView = (TextView) findViewById(R.id.empty_view);

        earthquakeListView.setAdapter(itemsAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Earthquake currentEarthquake = itemsAdapter.getItem(position);
                String url = currentEarthquake.getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        Log.i(TAG, "checking internet");
        if (isConnected) {
            Log.i(TAG, "initializing loader");
            getLoaderManager().initLoader(0, null, this);
        } else {
            Log.i(TAG, "no internet connection");
            View progressBar = (View) findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
            emptyTextView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.i(TAG, "creating loader");
        return new EarthquakeLoader(EarthquakeActivity.this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        Log.i(TAG, "loader finished");
        // hide progress bar
        View progressBar = (View) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        emptyTextView.setVisibility(View.VISIBLE);
        emptyTextView.setText(R.string.no_data);

        // clear adapter first
        itemsAdapter.clear();
        if (earthquakes != null && !earthquakes.isEmpty()) {
            Log.i(TAG, "number of earthquakes: " + earthquakes.size());
            itemsAdapter.addAll(earthquakes);
        } else {
            earthquakeListView.setEmptyView(emptyTextView);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        Log.i(TAG, "reseting loader");
        itemsAdapter.clear();
    }
}
