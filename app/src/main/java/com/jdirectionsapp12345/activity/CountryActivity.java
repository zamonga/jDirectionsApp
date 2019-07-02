package com.jdirectionsapp12345.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.jdirectionsapp12345.R;
import com.jdirectionsapp12345.adapter.CountryAdapter;
import com.jdirectionsapp12345.common.Share;
import com.jdirectionsapp12345.model.Country.CountryDataModel;
import com.jdirectionsapp12345.model.Country.CountryModel;
import com.jdirectionsapp12345.response.ApiClient;
import com.jdirectionsapp12345.response.ApiInterface;

public class CountryActivity extends AppCompatActivity {

    RecyclerView recyclelist;
    CountryAdapter countryAdapter;
    List<CountryDataModel> countryDataModelList = new ArrayList<>();
    ProgressDialog progressDialog;
    TextView placeholder, txt_title;
    ImageView img_back;

    public String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclelist = findViewById(R.id.recyclelist);
        placeholder = findViewById(R.id.placeholder);
        txt_title = findViewById(R.id.txt_title);
        img_back = findViewById(R.id.img_back);
        recyclelist.setLayoutManager(new LinearLayoutManager(CountryActivity.this));

        Intent genderExtra = getIntent();
        gender = genderExtra.getExtras().getString("gender");

        Log.i("GenderSelected",gender);

        countryAdapter = new CountryAdapter(countryDataModelList, R.layout.city_list_item, CountryActivity.this,gender);
        recyclelist.setAdapter(countryAdapter);
        getCountryList();

        //Typeface font = Typeface.createFromAsset(getAssets(), "Product Sans Regular.ttf");
        //txt_title.setTypeface(font);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CountryActivity.this,GenderActivity.class);
                startActivity(intent);
            }
        });


    }

    public void getCountryList() {

        if(!Share.NoInternetConnection(CountryActivity.this))
        {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            progressDialog = new ProgressDialog(CountryActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            Call<CountryModel> call = apiService.getCountryList(gender);
            call.enqueue(new Callback<CountryModel>() {

                @Override
                public void onResponse(Call<CountryModel> call, Response<CountryModel> response) {
                    countryDataModelList.clear();
                    countryDataModelList.addAll(response.body().getData());
                    countryAdapter.notifyDataSetChanged();
                    if (response.body().getError().equals("true")) {
                        placeholder.setVisibility(View.VISIBLE);
                        recyclelist.setVisibility(View.GONE);
                    } else {
                        placeholder.setVisibility(View.GONE);
                        recyclelist.setVisibility(View.VISIBLE);
                    }
                    Log.d("TAG", "Number of movies received: " + countryDataModelList.size());
                    progressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<CountryModel> call, Throwable t) {
                    Log.e("TAG", t.toString());
                    progressDialog.dismiss();
                }
            });
        }
    }
}
