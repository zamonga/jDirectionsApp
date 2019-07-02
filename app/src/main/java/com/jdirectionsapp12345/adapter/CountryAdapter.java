package com.jdirectionsapp12345.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.jdirectionsapp12345.R;
import com.jdirectionsapp12345.activity.CityListActivity;
import com.jdirectionsapp12345.model.Country.CountryDataModel;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CityViewHolder> {

    private List<CountryDataModel> countryDataModels;
    private int rowLayout;
    Context context;
    String gender;

    public CountryAdapter(List<CountryDataModel> countryDataModels, int rowLayout, Context context, String gender) {
        this.countryDataModels = countryDataModels;
        this.rowLayout = rowLayout;
        this.context = context;
        this.gender = gender;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CityViewHolder holder, int i) {
        holder.txt_city.setText(countryDataModels.get(i).getName());
        holder.txt_count.setText(countryDataModels.get(i).getCount());
        holder.txt_city.setTag(i);
        holder.txt_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = (int) v.getTag();
                Intent intent = new Intent(context, CityListActivity.class);
                intent.putExtra("country_id", countryDataModels.get(i).getCountryId());
                intent.putExtra("name", countryDataModels.get(i).getName());
                intent.putExtra("gender",gender);
                context.startActivity(intent);
            }
        });

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Product Sans Regular.ttf");
        holder.txt_city.setTypeface(font);
        holder.txt_count.setTypeface(font);
    }

    @Override
    public int getItemCount() {
        return countryDataModels.size();
    }

    public static class CityViewHolder extends RecyclerView.ViewHolder {

        TextView txt_city, txt_count;

        public CityViewHolder(View v) {
            super(v);
            txt_city = v.findViewById(R.id.txt_city);
            txt_count = v.findViewById(R.id.txt_count);
        }
    }
}
