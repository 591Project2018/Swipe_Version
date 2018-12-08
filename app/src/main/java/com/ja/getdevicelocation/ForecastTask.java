package com.ja.getdevicelocation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
            ForecastAPI forecastAPI=new ForecastAPI();
           //String url=forecastAPI.createURL(Double.parseDouble(urls[0]),Double.parseDouble(urls[1]));
            String[] response=new String[3];
            URL forecastURL;

            try{
                forecastURL=new URL(urls[0]);
                response[0]=forecastAPI.makeAPICall(forecastURL);
                response[1]=urls[1];
                response[2]=urls[0];
            }catch (MalformedURLException e){

                e.printStackTrace();
            }catch (IOException e){

                e.printStackTrace();
            }

            return response;
        }



        @Override
        protected void onPostExecute( String[] response) {
            Log.i("real_response","Post"+response[0]);
           int i=Integer.parseInt(response[1]);
            ForecastAPI forecastAPI=new ForecastAPI();

            List<DailyWeather> res=forecastAPI.getGeoForecast(response[0]);
            Intent intent=new Intent(mainContext,activityClass);
            intent.putExtra("city",res.get(i).getCity());
            intent.putExtra("pressure", ""+res.get(i).getPressure());
           //intent.putExtra("mainWeather2Image",res.get(0).getDiscription());
            intent.putExtra("descp",res.get(i).getDiscription());
            double temp2=res.get(0).getTemMax()/2+res.get(1).getTemMax()/2;
            temp2=(double)(Math.round(temp2*100)/100.0);
            //intent.putExtra("mainWeather2", res.get(0).getDiscription()+"\n\n "+temp2+" °C \n\n Humid: \n"+String.valueOf(res.get(0).getHumid())+"\n\n Pressure: \n"+String.valueOf(res.get(0).getPressure()));
            intent.putExtra("day2Temp",String.valueOf(temp2));
            intent.putExtra("forecastURL",response[2]);
            mainContext.startActivity(intent);



        }
    }

