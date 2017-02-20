package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomEarthquakeAdapter extends ArrayAdapter<Earthquake> {
    int magnitudeColor;
    Context context;
    public CustomEarthquakeAdapter(Context context, ArrayList<Earthquake> objects) {
        super(context, 0, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.my_list, null);

        //finding elements of the layout
        LinearLayout listMain = (LinearLayout) convertView.findViewById(R.id.listMain);
        TextView mag = (TextView) convertView.findViewById(R.id.mag);
        TextView place = (TextView) convertView.findViewById(R.id.place);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        TextView near = (TextView) convertView.findViewById(R.id.near);

        //set the proper background color on the magnitude circle.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();


        //setting resources
        final Earthquake getQuake = getItem(position);

        //get the appropriate background color based on the current earthquake magnitude
        magnitudeColor = getMagnitudeColor(getQuake.returnMag());

        //set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        String location = getQuake.returnPlace();
        String[] split;
        String offset, primary;
        mag.setText(String.valueOf(getQuake.returnMag()));
        if (location.contains("of")) {
            split = location.split("of");
            offset = split[0];
            primary = split[1];
            near.setText(String.valueOf(offset) + "of");
        } else {
            near.setText("Near the");
            primary = getQuake.returnPlace();
        }
        place.setText(String.valueOf(primary));
        date.setText(getQuake.returnDate());
        time.setText(getQuake.returnTime());

        listMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = getQuake.returnUrl();
                Intent openUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(openUrl);
            }
        });

        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        switch ((int) magnitude) {
            case 0:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 1:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 2:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 3:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 4:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 5:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 6:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 7:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 8:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            case 9:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
            default:
                magnitudeColor = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }
        return magnitudeColor;
    }
}
