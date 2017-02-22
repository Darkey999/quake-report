package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public final class QueryUtils {

    private QueryUtils() {
    }

    // Returns URL from the given String
    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e("Error with creating URL", String.valueOf(exception));
            return null;
        }
        return url;
    }

    // Establishing connection with the chosen URL
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        if (url == null)
            return jsonResponse;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else Log.e("httpError", "responseCode is not 200");
        } catch (IOException e) {
            Log.e("httpError", String.valueOf(e));
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    /* Function created for parsing data from JSON, then adding new Earthquake object
       with the specified parameters to the ArrayList
    */
    public static ArrayList<Earthquake> extractEarthquakes(String jsonResponse) throws IOException {

        // Create an empty ArrayList that we can add earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Parse data from the JSON
        try {
            JSONObject reader = new JSONObject(jsonResponse);
            JSONArray features = reader.getJSONArray("features");

            for (int i = 0; i < features.length(); i++) {
                JSONObject f = features.getJSONObject(i);
                JSONObject prop = f.getJSONObject("properties");
                double mag = prop.getDouble("mag");
                String place = prop.getString("place");
                long time = prop.getLong("time");
                String url = prop.getString("url");

                //convert milliseconds to actual date
                Date dateObject = new Date(time);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
                SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
                String dateToDisplay = dateFormatter.format(dateObject);
                String timeToDisplay = timeFormatter.format(dateObject);
                earthquakes.add(new Earthquake(mag, place, dateToDisplay, timeToDisplay, url));
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

    // Function used to read the whole information and put in in the one String
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}