package com.jdirectionsapp12345.activity;

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

import com.jdirectionsapp12345.R;
import com.jdirectionsapp12345.adapter.CityListAdapter;
import com.jdirectionsapp12345.common.Share;
import com.jdirectionsapp12345.model.City.CityDataModel;
import com.jdirectionsapp12345.model.City.CityModel;
import com.jdirectionsapp12345.response.ApiClient;
import com.jdirectionsapp12345.response.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityListActivity extends AppCompatActivity {

    RecyclerView recycle_city_sublist;
    CityListAdapter cityListAdapter;
    List<CityDataModel> cityModelList = new ArrayList<>();
    String position, CountryName;
    TextView placeholder, txt_title;
    ProgressDialog progressDialog;
    ImageView img_back, img_home;
    String gender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_subcategory);

        placeholder = findViewById(R.id.placeholder);
        Intent i = getIntent();
        position = i.getExtras().getString("country_id");
        CountryName = i.getExtras().getString("name");
        gender = i.getExtras().getString("gender");

        Log.i("GenderSelectedCityList",gender);

        recycle_city_sublist = findViewById(R.id.recycle_city_sublist);
        img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title);
        img_home = findViewById(R.id.img_home);

        txt_title.setText(CountryName);

        recycle_city_sublist.setLayoutManager(new LinearLayoutManager(CityListActivity.this));
        cityListAdapter = new CityListAdapter(cityModelList, R.layout.city_list_item, CityListActivity.this,gender);
        recycle_city_sublist.setAdapter(cityListAdapter);
        getSubCityList();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CityListActivity.this, CountryActivity.class);
                i.putExtra("gender",gender);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        });

        //Typeface font = Typeface.createFromAsset(getAssets(), "Product Sans Regular.ttf");
        //txt_title.setTypeface(font);
    }

    public void getSubCityList() {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        progressDialog = new ProgressDialog(CityListActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        Share.NoInternetConnection(CityListActivity.this);
        Call<CityModel> call = apiService.getSubCityList(position,gender);
        call.enqueue(new Callback<CityModel>() {

            @Override
            public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                cityModelList.clear();
                cityModelList.addAll(response.body().getData());
                cityListAdapter.notifyDataSetChanged();
                if (response.body().getError().equals("true")) {
                    placeholder.setVisibility(View.VISIBLE);
                    recycle_city_sublist.setVisibility(View.GONE);
                } else {
                    placeholder.setVisibility(View.GONE);
                    recycle_city_sublist.setVisibility(View.VISIBLE);
                }
                Log.d("TAG", "Number of subcity: " + cityModelList.size());
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {
                Log.e("TAG", t.toString());
                progressDialog.dismiss();
            }
        });
    }
}
