package com.vhp.quakeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mQuakeListView;

    private List<Info> mInfoList;


    private final String URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ui typecasting

        mQuakeListView = (ListView) findViewById(R.id.listView_quake);

        // dummmy placeolder date

        mInfoList = new ArrayList<>();

      /*  mInfoList.add(new Info((float) 7.2, "San Francisco" , "Feb 2 , 2016"));
        mInfoList.add(new Info((float) 6.1, "London" , "July 20 , 2015"));
        mInfoList.add(new Info((float) 3.9, "Japan" , "Nov 10 , 2014"));
        mInfoList.add(new Info((float) 5.4, "Mexico City" , "Jan 28 , 2007"));
        mInfoList.add(new Info((float) 2.8, "Moscow" , "May 3 , 2014"));
        mInfoList.add(new Info((float) 4.9, "Rio de Janeiro" , "Aug 12 , 2012"));
        mInfoList.add(new Info((float) 1.6, "Paris" , "Oct 30 , 2011"));*/

//      mInfoList = QueryUtils.extractEarthquakes();

        new EarthquakeAsyncTask().execute(URL);


        final List<Info> finalMInfoList = mInfoList;
        mQuakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = finalMInfoList.get(position).getUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent );

            }
        });
    }


    private class EarthquakeAsyncTask  extends AsyncTask<String , Void , List<Info>>{

        @Override
        protected List<Info> doInBackground(String... params) {

            mInfoList = QueryUtils.fetchEarthquakeData(params[0]);

            return mInfoList;
        }

        @Override
        protected void onPostExecute(List<Info> infos) {
            super.onPostExecute(infos);
            QuakeRowAdapter mQualkeRowAdapter = new QuakeRowAdapter(
                    MainActivity.this ,
                    R.layout.layout_row,
                    infos
            );

            mQuakeListView.setAdapter(mQualkeRowAdapter);

        }
    }
}
