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
        TextView time = (TextView) convertView.findViewById(R.id.time);
        TextView near = (TextView) convertView.findViewById(R.id.near);

        //setting resources
        Earthquake getQuake = getItem(position);

        String location = getQuake.returnPlace();
        String[] split;
        String offset, primary;
        mag.setText(String.valueOf(getQuake.returnMag()));
        if (location.contains("of")) {
            split = location.split("of");
            offset = split[0];
            primary = split[1];
            near.setText(String.valueOf(offset)+"of");
        } else {
            near.setText("Near the");
            primary = getQuake.returnPlace();
        }
        place.setText(String.valueOf(primary));
        date.setText(getQuake.returnDate());
        time.setText(getQuake.returnTime());
        return convertView;
    }
}
