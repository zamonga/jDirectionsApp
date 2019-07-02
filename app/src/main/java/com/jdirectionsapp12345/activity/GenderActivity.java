package com.jdirectionsapp12345.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jdirectionsapp12345.R;

public class GenderActivity extends AppCompatActivity {

    Button btn_gender_male;
    Button btn_gender_female;
    TextView tv_select_gender;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        btn_gender_male = findViewById(R.id.btn_gender_male);
        btn_gender_female = findViewById(R.id.btn_gender_female);
        tv_select_gender = findViewById(R.id.tv_select_gender);

        btn_gender_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "male";
                Intent intent = new Intent(GenderActivity.this, CountryActivity.class);
                intent.putExtra("gender",gender);
                startActivity(intent);
            }
        });

        btn_gender_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "female";
                Intent intent = new Intent(GenderActivity.this, CountryActivity.class);
                intent.putExtra("gender",gender);
                startActivity(intent);
            }
        });

        //Setting font
        Typeface font = (Typeface) Typeface.createFromAsset(this.getAssets(), "fonts/robotoslab.ttf");

        tv_select_gender.setTypeface(font);
        btn_gender_male.setTypeface(font);
        btn_gender_female.setTypeface(font);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
