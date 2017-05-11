package com.vhp.quakeapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by root on 5/10/17.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Info>> {

    private static final String TAG = "MainActivity";

    private final String URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";


    public EarthquakeLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        Log.d(TAG, "onStartLoading: ");
        forceLoad();
    }

    @Override
    public List<Info> loadInBackground() {
        Log.d(TAG, "loadInBackground: ");
        if(URL==null){
            return null;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Info> mInfoList = QueryUtils.fetchEarthquakeData(URL);
        return mInfoList;
    }
}
