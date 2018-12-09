package com.ja.getdevicelocation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



    public class ForecastTask extends AsyncTask<String, Void, String[]> {
            Context mainContext;
            Class activityClass;


        public ForecastTask(Context mainContext, Class activityClass){
            this.mainContext=mainContext;
            this.activityClass=activityClass;

        }

        @Override
        protected String[] doInBackground(String... urls) {
            ForecastAPI forecastAPI = new ForecastAPI();
           // String url=forecastAPI.createURL(Double.parseDouble(geos[0]),Double.parseDouble(geos[1]));
            String[] response=new String[3];
            URL forecastURL;

            try {

                forecastURL = new URL(urls[0]);
                response[0]=forecastAPI.makeAPICall(forecastURL);
                response[1]=urls[1];
                response[2]=urls[0];



            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }

            return response;
        }


        @Override
        protected void onPostExecute(String[] response) {
            int i=Integer.parseInt(response[1]);
            ForecastAPI forecastAPI = new ForecastAPI();

            List<DailyWeather> res =forecastAPI.getGeoForecast(response[0]);

            Intent intent = new Intent(mainContext, activityClass);

            intent.putExtra("city",res.get(i).getCity());
            intent.putExtra("pressure",res.get(2*i-2).getPressure());
            intent.putExtra("humid",res.get(2*i-2).getHumid());
            intent.putExtra("mainWeather",res.get(2*i-2).getDiscription());

            double temp2=res.get(0).getTemMax()/2+res.get(1).getTemMax()/2;
            temp2=(double)(Math.round(temp2*100)/100.0);
            intent.putExtra("day2Temp",String.valueOf(temp2));
            double temp3=res.get(2).getTemMax()/2+res.get(3).getTemMax()/2;
            temp3=(double)(Math.round(temp3*100)/100.0);
            intent.putExtra("day3Temp",String.valueOf(temp3));
            double temp4=res.get(4).getTemMax()/2+res.get(5).getTemMax()/2;
            temp4=(double)(Math.round(temp4*100)/100.0);
            intent.putExtra("day4Temp",String.valueOf(temp4));
            double temp5=res.get(6).getTemMax()/2+res.get(7).getTemMax()/2;
            temp5=(double)(Math.round(temp5*100)/100.0);
            intent.putExtra("day5Temp",String.valueOf(temp5));
            intent.putExtra("forecastURL",response[2]);
            mainContext.startActivity(intent);


            // intent.putExtra("mainWeather6Image",res.get(34).getDiscription());
            //double temp6=res.get(34).getTemMax()/2+res.get(37).getTemMax()/2;
            //temp6=(double)(Math.round(temp6*100)/100.0);
            //intent.putExtra("mainWeather6", res.get(34).getDiscription()+"\n\n "+temp6+" °C \n\n Humid: \n"+String.valueOf(res.get(34).getHumid())+"\n\n Pressure: \n"+String.valueOf(res.get(34).getPressure()));





        }
    }

