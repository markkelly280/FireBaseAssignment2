package com.example.firebaseassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class electric_cars_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_cars_info);

        ImageView imageView = findViewById(R.id.myImageView);
        TextView textView = findViewById(R.id.myTextView);


        imageView.setImageResource(R.drawable.car_image);
        textView.setText("\n" +
                "Electric cars are integral to promoting the utilization of renewable energy in the transportation sector. By relying on electricity as a power source, electric vehicles (EVs) contribute to a reduction in greenhouse gas emissions compared to traditional internal combustion engine vehicles. When charged using electricity generated from renewable sources such as solar, wind, or hydroelectric power, electric cars significantly lower their overall carbon footprint. This synergy between electric vehicles and renewable energy aligns with efforts to combat climate change and transition toward a more sustainable energy future. As the grid becomes increasingly powered by clean energy, the environmental benefits of electric cars continue to grow, making them a key component in fostering a more sustainable and low-carbon transportation ecosystem.");
    }
}