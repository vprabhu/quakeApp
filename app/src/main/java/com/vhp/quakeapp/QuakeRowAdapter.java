package com.vhp.quakeapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 15/4/17.
 */

public class QuakeRowAdapter extends ArrayAdapter<Info> {


    private Context mContext;
    public QuakeRowAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Info> objects) {
        super(context, resource, objects);
        mContext = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder ;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.layout_row , parent , false);
            holder = new ViewHolder();
            holder.mMagnitudeTextView = (TextView) convertView.findViewById(R.id.textView_magnitude);
            holder.mLocationTextView = (TextView) convertView.findViewById(R.id.textView_location);
            holder.mLocationNearTextView = (TextView) convertView.findViewById(R.id.textView_location_near);
            holder.mDateTextView = (TextView) convertView.findViewById(R.id.textView_date);
            holder.mTimeTextView = (TextView) convertView.findViewById(R.id.textView_time);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        // sets the UI data

        Info mInfo = getItem(position);

        GradientDrawable magnitudeCircle = (GradientDrawable) holder.mMagnitudeTextView.getBackground();
        int magnitudeColor = getMagnitudeColor(mInfo.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);
        DecimalFormat formatter = null;
        formatter = new DecimalFormat("0.00");
        String output = null;
        output = formatter.format(mInfo.getMagnitude());
        holder.mMagnitudeTextView.setText(output);

        String location = mInfo.getLocation();
        String locationSplit[] = location.split(",");
        String locationOne;
        String locationTwo;
        if(locationSplit.length==2){
            locationOne = locationSplit[0];
            locationTwo = locationSplit[1];
        }else {
            locationOne = locationSplit[0];
            locationTwo = "Near the";
        }

        holder.mLocationTextView.setText(locationTwo);
        holder.mLocationNearTextView.setText(locationOne);

        long timeInMilliseconds = mInfo.getDate();
        Date dateObject = new Date(timeInMilliseconds);
        holder.mDateTextView.setText(formatDate(dateObject));
        holder.mTimeTextView.setText(formatTime(dateObject));

        return convertView;
    }


    static class ViewHolder{
        private TextView mMagnitudeTextView;
        private TextView mLocationTextView;
        private TextView mLocationNearTextView;
        private TextView mDateTextView;
        private TextView mTimeTextView;

    }

    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);

        return dateToDisplay;
    }


    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        String dateToDisplay = timeFormatter.format(dateObject);

        return dateToDisplay;
    }

    private int getMagnitudeColor(double magnitude){
        int magnitudeResourceId = 0;
        int magnitudeColor = (int) Math.floor(magnitude);
        switch (magnitudeColor){
            case 0:
            case 1:
                magnitudeResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeResourceId = R.color.magnitude9;
                break;
            case 10:
                magnitudeResourceId = R.color.magnitude10plus;
                break;
            default:
                magnitudeResourceId = R.color.magnitude10plus;
                break;

        }
        return ContextCompat.getColor(getContext() , magnitudeResourceId);
    }
}
