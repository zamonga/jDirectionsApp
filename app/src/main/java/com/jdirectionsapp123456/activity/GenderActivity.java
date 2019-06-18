package com.jdirectionsapp123456.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jdirectionsapp123456.R;

public class GenderActivity extends AppCompatActivity {

    TextView tv_gender_male;
    TextView tv_gender_female;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        tv_gender_male = findViewById(R.id.tv_gender_male);
        tv_gender_female = findViewById(R.id.tv_gender_female);

        tv_gender_male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "male";
                Intent intent = new Intent(GenderActivity.this,MainActivity.class);
                intent.putExtra("gender",gender);
                startActivity(intent);
            }
        });

        tv_gender_female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gender = "female";
                Intent intent = new Intent(GenderActivity.this,MainActivity.class);
                intent.putExtra("gender",gender);
                startActivity(intent);
            }
        });
    }
}
