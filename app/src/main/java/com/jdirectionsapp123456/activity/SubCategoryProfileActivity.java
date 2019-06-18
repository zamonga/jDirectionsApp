package com.jdirectionsapp123456.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdirectionsapp123456.R;
import com.jdirectionsapp123456.adapter.ProfileAdapter;
import com.jdirectionsapp123456.common.Share;
import com.jdirectionsapp123456.model.profile.ProfileDataModel;
import com.jdirectionsapp123456.model.profile.ProfileModel;
import com.jdirectionsapp123456.response.ApiClient;
import com.jdirectionsapp123456.response.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryProfileActivity extends AppCompatActivity {

    GridView grid_view;
    String category_id, city_id, gender;
    ArrayList<ProfileDataModel> profileDataModelArrayList = new ArrayList<>();
    ProgressDialog progressDialog;
    ImageView img_back, img_home;
    TextView placeholder, txt_title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory_profile);

        Intent i = getIntent();
//        category_id = i.getExtras().getString("id");
        city_id = i.getExtras().getString("city_id");
        gender = i.getExtras().getString("gender");

        Log.i("GenderProfileList",gender);

        placeholder = findViewById(R.id.placeholder);
        grid_view = findViewById(R.id.grid_view);
        img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title);
        img_home = findViewById(R.id.img_home);

        txt_title.setText(gender);
        getSubCityList();

        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getApplicationContext(), FullImageActivity.class);
                intent.putExtra("image", profileDataModelArrayList.get(i).getImage());
                startActivity(intent);
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(SubCategoryProfileActivity.this, MainActivity.class);
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
        progressDialog = new ProgressDialog(SubCategoryProfileActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        Share.NoInternetConnection(SubCategoryProfileActivity.this);
        Call<ProfileModel> call = apiService.getProfileList(city_id, gender);
        call.enqueue(new Callback<ProfileModel>() {

            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                profileDataModelArrayList.clear();
                profileDataModelArrayList.addAll(response.body().getData());
                grid_view.setAdapter(new ProfileAdapter(profileDataModelArrayList, SubCategoryProfileActivity.this));
                if (response.body().getError().equals("true")) {
                    placeholder.setVisibility(View.VISIBLE);
                    grid_view.setVisibility(View.GONE);
                } else {
                    placeholder.setVisibility(View.GONE);
                    grid_view.setVisibility(View.VISIBLE);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Log.e("TAG", t.toString());
                progressDialog.dismiss();
            }
        });
    }
}
