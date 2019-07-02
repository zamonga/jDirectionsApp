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
import com.jdirectionsapp12345.model.category.CategoryListModel;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CityViewHolder> {

    private List<CategoryListModel> categoryListModels;
    private int rowLayout;
    Context context;
    String city_id;

    public CategoryListAdapter(List<CategoryListModel> categoryListModels, int rowLayout, Context context, String city_id) {
        this.categoryListModels = categoryListModels;
        this.rowLayout = rowLayout;
        this.context = context;
        this.city_id = city_id;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CityViewHolder holder, int i) {

        holder.txt_city.setText(categoryListModels.get(i).getName());
        holder.txt_count.setText(categoryListModels.get(i).getCount());
        Log.e("TAG", "onBindViewHolder: " + categoryListModels.get(i).getName());

        holder.txt_city.setTag(i);
        holder.txt_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                Intent intent = new Intent(context, SubCategoryProfileActivity.class);
                intent.putExtra("city_id", city_id);
                intent.putExtra("id", categoryListModels.get(pos).getId());
                intent.putExtra("name", categoryListModels.get(pos).getName());
                context.startActivity(intent);
            }
        });

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Product Sans Regular.ttf");
        holder.txt_city.setTypeface(font);
        holder.txt_count.setTypeface(font);
    }

    @Override
    public int getItemCount() {
        return categoryListModels.size();
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
