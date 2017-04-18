package com.vhp.quakeapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
            holder.mDateTextView = (TextView) convertView.findViewById(R.id.textView_date);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        // sets the UI data

        Info mInfo = getItem(position);

        holder.mDateTextView.setText(mInfo.getDate());
        holder.mLocationTextView.setText(mInfo.getLocation());
        holder.mMagnitudeTextView.setText(""+mInfo.getMagnitude());

        return convertView;
    }


    static class ViewHolder{
        private TextView mMagnitudeTextView;
        private TextView mLocationTextView;
        private TextView mDateTextView;

    }
}
