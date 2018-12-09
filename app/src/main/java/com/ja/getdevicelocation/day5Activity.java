package com.ja.getdevicelocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class day5Activity extends AppCompatActivity {
    private TextView real_weather, real_pressure, location,real_temperature,real_humid;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_display5);
        Intent intent=getIntent();
        String city=intent.getStringExtra("city");
        String pressure=intent.getStringExtra("pressure");
        String mainWeather=intent.getStringExtra("mainWeather");
        String humid=intent.getStringExtra("humid");
        String temperature=intent.getStringExtra("day5Temp");
        location=findViewById(R.id.location5);
        real_weather=findViewById(R.id.weather5);
        real_pressure=findViewById(R.id.pressure5);
        real_temperature=findViewById(R.id.temperature5);

        real_humid=findViewById(R.id.humid2);
        ImageView view=findViewById(R.id.WeatherImage5);

        if(mainWeather.contains("Rain")) {
            view.setImageResource(R.drawable.rainy);
        }else if (mainWeather.contains("rain")) {
            view.setImageResource(R.drawable.rainy);
        }else if (mainWeather.contains("snow")) {
            view.setImageResource(R.drawable.snow);
        }else if (mainWeather.contains("Snow")) {
            view.setImageResource(R.drawable.snow);
        } else if (mainWeather.contains("Cloud")) {
            view.setImageResource(R.drawable.cloudy);
        } else if (mainWeather.contains("cloud")) {
            view.setImageResource(R.drawable.cloudy);
        } else if (mainWeather.contains("Clear")) {
            view.setImageResource(R.drawable.sunny);
        } else if (mainWeather.contains("clear")) {
            view.setImageResource(R.drawable.sunny);
        } else if (mainWeather.contains("sun")) {
            view.setImageResource(R.drawable.sunny);
        }else if (mainWeather.contains("fog")) {
            view.setImageResource(R.drawable.fog);
        }else if (mainWeather.contains("haze")) {
            view.setImageResource(R.drawable.fog);
        }else if (mainWeather.contains("Fog")) {
            view.setImageResource(R.drawable.fog);
        }else if (mainWeather.contains("Haze")) {
            view.setImageResource(R.drawable.fog);
        }else{
            view.setImageResource(R.drawable.sunny);
        }

        location.setText(city+ " Day5");
        real_weather.setText(mainWeather);
        real_pressure.setText(pressure);
        real_temperature.setText(temperature);
        real_humid.setText(humid);
        url=intent.getStringExtra("forecastURL");


        Date now=new Date();
        Calendar calendar=new GregorianCalendar();
        calendar.add(calendar.DATE,4);
        Date tom2=calendar.getTime();
        String todayString=new SimpleDateFormat("yyyy-MM-dd").format(tom2);
        TextView third=findViewById(R.id.Day5);
        third.setText(todayString);
    }
}

