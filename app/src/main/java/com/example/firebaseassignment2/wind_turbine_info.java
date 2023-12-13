package com.example.firebaseassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class wind_turbine_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wind_turbine_info);

        ImageView imageView = findViewById(R.id.myImageView);
        TextView textView = findViewById(R.id.myTextView);


        imageView.setImageResource(R.drawable.wind_mill);
        textView.setText("Wind turbines play a pivotal role in advancing renewable energy by harnessing the power of the wind to generate electricity. As a clean and sustainable energy source, wind power significantly reduces reliance on fossil fuels, contributing to a lower carbon footprint and mitigating the impacts of climate change. Wind turbines operate without emitting greenhouse gases during electricity generation, offering an environmentally friendly alternative to traditional energy sources. The scalability of wind farms, coupled with continuous and predictable wind resources, ensures a reliable and constant power supply. Beyond environmental benefits, wind energy projects stimulate economic growth, create job opportunities, and enhance energy independence. As technological advancements continue to improve efficiency and reduce costs, wind turbines stand as a key component in the global transition towards a more sustainable and resilient energy infrastructure");
    }
}