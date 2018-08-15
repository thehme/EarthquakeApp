package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    private static final String TAG = EarthquakeLoader.class.getSimpleName();
    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(TAG, "on start loading");
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        Log.i(TAG, "load in background");
        if (mUrl == null) {
            Log.i(TAG, "No url passed");
            return null;
        }
        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        return earthquakes;
    }
}
