package com.example.nouri.myapplication;

/**
 * Created by nouri on 19/12/2016.
 */

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Countries> data = Collections.emptyList();
    Countries current;
    int currentPos = 0;

    public CountriesAdapter(Context context, List<Countries> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_blank, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        Countries current = data.get(position);
        myHolder.countryName.setText(current.name);
        myHolder.countryId.setText("Size: " + current.id);


    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView countryName;

        TextView countryId;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            countryName = (TextView) itemView.findViewById(R.id.name);
            countryId = (TextView) itemView.findViewById(R.id.id);
        }

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

}