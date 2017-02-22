/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    final String CONNECT_URL = "http://earthquake.usgs.gov/fdsnws/event/1/" +
            "query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    CustomEarthquakeAdapter adapter;
    ArrayList<Earthquake> earthquakes;
    ListView earthquakeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        earthquakeListView = (ListView) findViewById(R.id.list);

        // Execute new EarthquakeAsyncTask
        EarthquakeAsyncTask earthQuakeResult = new EarthquakeAsyncTask();
        earthQuakeResult.execute(CONNECT_URL);
    }

    // Class created to handle network operations in the background thread
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            // Creating URL
            URL connection = QueryUtils.createUrl(urls[0]);
            String jsonResponse;
            try {
                // Establishing connection,
                jsonResponse = QueryUtils.makeHttpRequest(connection);
                earthquakes = QueryUtils.extractEarthquakes(jsonResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakesResult) {
            // Setting adapter
            adapter = new CustomEarthquakeAdapter(EarthquakeActivity.this, earthquakes);
            earthquakeListView.setAdapter(adapter);
        }
    }
}
