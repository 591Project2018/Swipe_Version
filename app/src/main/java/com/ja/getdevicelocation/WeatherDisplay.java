package com.ja.getdevicelocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class WeatherDisplay extends AppCompatActivity {
    private TextView real_weather, real_pressure, location,temp;
    private String lat, lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_display);

        Intent intent=getIntent();
        String city=intent.getStringExtra("city");
        String pressure=intent.getStringExtra("pressure");
        String mainWeather=intent.getStringExtra("mainWeather");
        location=findViewById(R.id.location);
        real_weather=findViewById(R.id.weather);
        real_pressure=findViewById(R.id.pressure);
        temp=findViewById(R.id.temp);
        location.setText(city);
        real_weather.setText(mainWeather);
        real_pressure.setText(pressure);
        String tempMax=intent.getStringExtra("temp");
        temp.setText(tempMax);

         this.lat=intent.getStringExtra("lat");
         this.lon=intent.getStringExtra("lon");

        //Log.i("Main_response1",this.lat);
        location.setOnTouchListener(new OnSwipeTouchListener(WeatherDisplay.this) {
            public void onSwipeTop() {
                Toast.makeText(WeatherDisplay.this, "top", Toast.LENGTH_SHORT).show();
                //Toast.makeText(WeatherDisplay.this,  "Sorry, couldn't get your location!", Toast.LENGTH_LONG).show();

            }
            public void onSwipeRight() {
                ForecastAPI forecastAPI=new ForecastAPI();
                String url=forecastAPI.createURL(Double.parseDouble(lat),Double.parseDouble(lon));
                Toast.makeText(WeatherDisplay.this, "right", Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(WeatherDisplay.this, Day2.class);
                ForecastTask forecastTask=new ForecastTask(WeatherDisplay.this, Day2.class);
                forecastTask.execute(url,"0"); //checked
//                ForecastAPI forecastAPI=new ForecastAPI();


            }
            public void onSwipeLeft() {
                Toast.makeText(WeatherDisplay.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(WeatherDisplay.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
