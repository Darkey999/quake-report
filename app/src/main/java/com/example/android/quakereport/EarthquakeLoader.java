package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Gosu on 2017-02-24.
 */

// Class created for using Loader instead of AsyncTask
public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {
    String url;
    Context context;
    ArrayList<Earthquake> earthquakes;

    // Constructor
    public EarthquakeLoader(Context context, String url, ArrayList<Earthquake> earthquakes) {
        super(context);
        this.url = url;
        this.context = context;
        this.earthquakes = earthquakes;
    }

    // Used to start the Loader
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    // Work in background thread
    @Override
    public ArrayList<Earthquake> loadInBackground() {
        // Creating URL
        URL connection = QueryUtils.createUrl(url);
        String jsonResponse;
        try {
            // Establishing connection,
            jsonResponse = QueryUtils.makeHttpRequest(connection);
            earthquakes = QueryUtils.extractEarthquakes(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return earthquakes;
    }
}
