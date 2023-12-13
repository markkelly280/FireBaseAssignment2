package com.example.firebaseassignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class solar_panel_info extends AppCompatActivity implements SensorEventListener {

    private ImageView imageView;
    private TextView textView;
    private SensorManager sensorManager;
    private Sensor lightSensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solar_panel_info);

        ImageView imageView = findViewById(R.id.myImageView);
        TextView textView = findViewById(R.id.myTextView);


        imageView.setImageResource(R.drawable.solar_panel_farm);
        textView.setText("Solar panels play a pivotal role in advancing renewable energy by harnessing the abundant and clean power of the sun. As photovoltaic cells within solar panels convert sunlight into electricity, they offer a sustainable and environmentally friendly alternative to traditional fossil fuels. Solar power systems produce electricity with minimal environmental impact, emitting no greenhouse gases or air pollutants during operation. The scalability of solar technology allows for installations ranging from small residential arrays to large utility-scale solar farms, contributing to a decentralized and diversified energy landscape. Additionally, advancements in solar technology, coupled with decreasing costs, have made solar energy increasingly accessible and economically viable. By tapping into an essentially infinite and renewable resource, solar panels are a key driver in the global transition toward a more sustainable and low-carbon energy future, offering both environmental and economic benefits.");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (lightSensor != null) {
                sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
    }
        Button home = (Button) findViewById(R.id.homeS_btn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent h = new Intent(solar_panel_info.this, MainActivity.class);
                startActivity(h);
            }
        });

}
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister the sensor listener to avoid memory leaks
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            // Get the light intensity value
            float lightIntensity = event.values[0];

            // You can use the light intensity value to adjust your UI or perform other actions
            // For example, you could change the background color based on light intensity
            if (lightIntensity < 10) {

            } else {

            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}


