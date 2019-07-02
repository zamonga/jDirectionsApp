package com.jdirectionsapp12345.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.jdirectionsapp12345.R;
import com.jdirectionsapp12345.activity.SubCategoryProfileActivity;
import com.jdirectionsapp12345.model.City.CityDataModel;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.CityViewHolder> {

    private List<CityDataModel> cityModels;
    private int rowLayout;
    Context context;
    String gender;

    public CityListAdapter(List<CityDataModel> cityModels, int rowLayout, Context context, String gender) {
        this.cityModels = cityModels;
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

        holder.txt_city.setText(cityModels.get(i).getCityName());
        holder.txt_count.setText(cityModels.get(i).getCount());
        Log.e("TAG", "onBindViewHolder: " + cityModels.get(i).getCityName());
        Log.e("TAG", "getCount: " + cityModels.get(i).getCount());

        holder.txt_city.setTag(i);
        holder.txt_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                Intent intent = new Intent(context, SubCategoryProfileActivity.class);
                intent.putExtra("city_id", cityModels.get(pos).getCityId());
                intent.putExtra("name", cityModels.get(pos).getCityName());
                intent.putExtra("gender", gender);
                context.startActivity(intent);
            }
        });

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Product Sans Regular.ttf");
        holder.txt_city.setTypeface(font);
        holder.txt_count.setTypeface(font);
    }

    @Override
    public int getItemCount() {
        return cityModels.size();
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
