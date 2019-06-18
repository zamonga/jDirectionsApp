package com.jdirectionsapp123456.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdirectionsapp123456.R;
import com.jdirectionsapp123456.adapter.CategoryListAdapter;
import com.jdirectionsapp123456.common.Share;
import com.jdirectionsapp123456.model.category.CategoryListModel;
import com.jdirectionsapp123456.model.category.CategoryModel;
import com.jdirectionsapp123456.response.ApiClient;
import com.jdirectionsapp123456.response.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListActivity extends AppCompatActivity {

    RecyclerView recycle_category_list;
    ImageView img_back,img_home;
    CategoryListAdapter categoryListAdapter;
    List<CategoryListModel> categoryListModels = new ArrayList<>();
    String city_id, CategoryList;
    TextView placeholder, txt_title;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        Intent i = getIntent();
        city_id = i.getExtras().getString("city_id");
        CategoryList = i.getExtras().getString("name");

        placeholder = findViewById(R.id.placeholder);
        txt_title = findViewById(R.id.txt_title);
        recycle_category_list = findViewById(R.id.recycle_category_list);
        img_back = findViewById(R.id.img_back);
        img_home = findViewById(R.id.img_home);

        txt_title.setText(CategoryList);

        recycle_category_list.setLayoutManager(new LinearLayoutManager(CategoryListActivity.this));
        categoryListAdapter = new CategoryListAdapter(categoryListModels, R.layout.city_list_item, CategoryListActivity.this, city_id);
        recycle_category_list.setAdapter(categoryListAdapter);
        getCategoryList();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CategoryListActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        //Typeface font = Typeface.createFromAsset(getAssets(), "Product Sans Regular.ttf");
        //txt_title.setTypeface(font);
    }

    public void getCategoryList() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        progressDialog = new ProgressDialog(CategoryListActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        Share.NoInternetConnection(CategoryListActivity.this);
        Call<CategoryModel> call = apiService.getCategoryList(city_id);
        call.enqueue(new Callback<CategoryModel>() {

            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                categoryListModels.clear();
                categoryListModels.addAll(response.body().getData());
                categoryListAdapter.notifyDataSetChanged();
                if (response.body().getError().equals("true")) {
                    placeholder.setVisibility(View.VISIBLE);
                    recycle_category_list.setVisibility(View.GONE);
                } else {
                    placeholder.setVisibility(View.GONE);
                    recycle_category_list.setVisibility(View.VISIBLE);
                }
                Log.d("TAG", "Number of subcity: " + categoryListModels.size());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.e("TAG", t.toString());
                progressDialog.dismiss();
            }
        });
    }
}
