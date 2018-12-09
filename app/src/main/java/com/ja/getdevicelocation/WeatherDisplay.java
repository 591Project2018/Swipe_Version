package com.ja.getdevicelocation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WeatherDisplay extends AppCompatActivity {
    private TextView real_weather, real_pressure, location,real_temperature,real_humid,real_tempMax,real_tempMin, real_Weather2,real_Weather3,real_Weather4,real_Weather5,real_Weather6;


    private String lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_display);
        Intent intent=getIntent();
        String city=intent.getStringExtra("city");
        String pressure=intent.getStringExtra("pressure");
        String mainWeather=intent.getStringExtra("mainWeather");

        //String mainWeather6=intent.getStringExtra("mainWeather6Image");
        //String mainWeather6Info=intent.getStringExtra("mainWeather6");

        ImageView view=findViewById(R.id.WeatherImage);

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



        Date now=new Date();

        Calendar calendar=new GregorianCalendar();
        calendar.add(calendar.DATE,1);
        Date tomorrow1=calendar.getTime();
        String nowAsString=new SimpleDateFormat("yyyy-MM-dd" ).format(now);
        TextView day=findViewById(R.id.Day);
        day.setText(nowAsString);


        //calendar.add(calendar.DATE,1);
        //Date tom5=calendar.getTime();
        //String sixthAsString=new SimpleDateFormat("MM-dd").format(tom5);
        //TextView sixth=findViewById(R.id.sixth);
        //sixth.setText(sixthAsString);

        String temperature=intent.getStringExtra("temperature");
        String tempMax=intent.getStringExtra("tempMax");
        String tempMin=intent.getStringExtra("tempMin");
        String humid=intent.getStringExtra("humid");
        location=findViewById(R.id.location);
        real_weather=findViewById(R.id.weather);
        real_pressure=findViewById(R.id.pressure);
        real_temperature=findViewById(R.id.temperature);
        real_tempMax=findViewById(R.id.tempMax);
        real_tempMin=findViewById(R.id.tempMin);
        real_humid=findViewById(R.id.humid);

        //real_Weather6 =findViewById(R.id.sixthInfo);
        location.setText(city);
        real_weather.setText(mainWeather);
        real_pressure.setText(pressure);
        real_temperature.setText(temperature);
        real_humid.setText(humid);
        real_tempMax.setText(tempMax);
        real_tempMin.setText(tempMin);


        //real_Weather6.setText(mainWeather6Info);

this.lat=intent.getStringExtra("lat");
this.lon=intent.getStringExtra("lon");

        location.setOnTouchListener(new OnSwipeTouchListener(WeatherDisplay.this) {
            public void onSwipeTop() {
                Toast.makeText(WeatherDisplay.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                ForecastAPI forecastAPI=new ForecastAPI();
                String url=forecastAPI.createURL(Double.parseDouble(lat),Double.parseDouble(lon));
                Toast.makeText(WeatherDisplay.this,"right",Toast.LENGTH_SHORT).show();
                ForecastTask forecastTask=new ForecastTask(WeatherDisplay.this,day2Activity.class);
                forecastTask.execute(url,"0");
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