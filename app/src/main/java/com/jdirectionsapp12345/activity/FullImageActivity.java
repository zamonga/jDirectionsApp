package com.jdirectionsapp12345.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.jdirectionsapp12345.R;

public class FullImageActivity extends AppCompatActivity {

    ImageView full_image_view, img_back, img_home;
    String image;
    TextView txt_title;
    ProgressDialog progressDialog;
    String gender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        Intent i = getIntent();
        image = i.getExtras().getString("image");
        gender = i.getExtras().getString("gender");

        img_back = findViewById(R.id.img_back);
        txt_title = findViewById(R.id.txt_title);
        full_image_view = findViewById(R.id.full_image_view);
        img_home = findViewById(R.id.img_home);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(FullImageActivity.this, CountryActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.putExtra("gender",gender);
                startActivity(i);
            }
        });


        Glide.with(FullImageActivity.this)
                .load(image)
                .fitCenter()
                .placeholder(R.drawable.load_1)
                .into(full_image_view);

        //Typeface font = Typeface.createFromAsset(getAssets(), "Product Sans Regular.ttf");
        //txt_title.setTypeface(font);

    }
}
