package com.example.android.quakereport;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gosu on 2017-02-19.
 */
public class CustomEarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public CustomEarthquakeAdapter(Context context, ArrayList<Earthquake> objects) {
        super(context, 0, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.my_list, null);

        //finding elements of the layout
        TextView mag = (TextView) convertView.findViewById(R.id.mag);
        TextView place = (TextView) convertView.findViewById(R.id.place);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        //setting resources
        Earthquake getQuake = getItem(position);
        mag.setText(String.valueOf(getQuake.returnMag()));
        place.setText(getQuake.returnPlace());
        date.setText(getQuake.returnDate());

        return convertView;
    }
}
