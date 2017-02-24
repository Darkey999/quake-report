package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Earthquake>> {

    final String CONNECT_URL = "http://earthquake.usgs.gov/fdsnws/event/1/" +
            "query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    CustomEarthquakeAdapter adapter;
    ArrayList<Earthquake> earthquakes;
    ListView earthquakeListView;
    TextView listTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        earthquakeListView = (ListView) findViewById(R.id.list);
        listTv = (TextView) findViewById(R.id.listTv);
        earthquakeListView.setEmptyView(listTv);

        getLoaderManager().initLoader(0, null, this);
    }

    // Creating new Loader
    @Override
    public Loader<ArrayList<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this, CONNECT_URL, earthquakes);
    }

    // Function handling situation after loading Loader
    @Override
    public void onLoadFinished(Loader<ArrayList<Earthquake>> loader, ArrayList<Earthquake> earthquakes) {
        adapter = new CustomEarthquakeAdapter(EarthquakeActivity.this, earthquakes);
        earthquakeListView.setAdapter(adapter);
        listTv.setText(R.string.no_earthquakes_found);
    }

    // Function used when the loader is being reset
    @Override
    public void onLoaderReset(Loader<ArrayList<Earthquake>> loader) {
        adapter.clear();
    }

}
