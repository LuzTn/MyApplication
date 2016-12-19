package com.example.nouri.myapplication;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class blankActivity extends AppCompatActivity {

    private RecyclerView mCountries;
    private CountriesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);


        List<Countries> data = new ArrayList<>();

        CountryUpdate cu = new CountryUpdate(this);

        try {

            JSONArray countt = cu.getCountriesFromFileba();
           Log.i("json", countt.toString());

            // Extract data from json and store into ArrayList as class objects
            for (int i = 0; i < countt.length(); i++) {
                JSONObject json_data = countt.getJSONObject(i);
                Countries count = new Countries();
                count.name = json_data.getString("name");
                count.id = json_data.getString("id");
Log.i("id: ",count.id);

                data.add(count);

            }


            // Setup and Handover data to recyclerview

            mCountries = (RecyclerView) findViewById(R.id.countries);


            mCountries.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

            mAdapter = new CountriesAdapter(this, data);
            mCountries.setAdapter(mAdapter);
            mCountries.smoothScrollToPosition(mAdapter.getItemCount());


        } catch (JSONException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }


    }

}
