package com.ja.getdevicelocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Day2 extends AppCompatActivity {
    private TextView real_weather, real_pressure, location,temp;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day2);
        Intent intent=getIntent();
        String city=intent.getStringExtra("city");
        String pressure=intent.getStringExtra("pressure");
        String mainWeather=intent.getStringExtra("descp");
        String tempMax=intent.getStringExtra("day2Temp");
        location=findViewById(R.id.location);
        real_weather=findViewById(R.id.weather);
        real_pressure=findViewById(R.id.pressure);
        temp=findViewById(R.id.temp);
        location.setText(city+" Day2");
        real_weather.setText(mainWeather);
        real_pressure.setText(pressure);
        url=intent.getStringExtra("forecastURL");
        temp.setText(tempMax);

        location.setOnTouchListener(new OnSwipeTouchListener(Day2.this) {
            public void onSwipeTop() {
                Toast.makeText(Day2.this, "top", Toast.LENGTH_SHORT).show();
                //Toast.makeText(WeatherDisplay.this,  "Sorry, couldn't get your location!", Toast.LENGTH_LONG).show();

            }
            public void onSwipeRight() {
                ForecastAPI forecastAPI=new ForecastAPI();

                Toast.makeText(Day2.this, "right", Toast.LENGTH_SHORT).show();
                //Intent intent=new Intent(WeatherDisplay.this, Day2.class);
                ForecastTask forecastTask=new ForecastTask(Day2.this, Day3.class);
                forecastTask.execute(url,"1"); //checked
//                ForecastAPI forecastAPI=new ForecastAPI();


            }
            public void onSwipeLeft() {
                Toast.makeText(Day2.this, "left", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeBottom() {
                Toast.makeText(Day2.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
