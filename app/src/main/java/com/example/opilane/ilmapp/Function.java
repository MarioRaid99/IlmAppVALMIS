package com.example.opilane.ilmapp;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class Function {
    private static final String OPEN_WEATHER_MAP_URL = "api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&units=metric";
    private static final String OPEN_WEATHER_MAP_API = "42bff55249321bdcc7adccae97bae6fd";

    public static String setWeatherIcon(int actualId,long sunrise, long sunset){
        int id = actualId /100;
        String icon = "";
        if (actualId == 800){
            long currentTime = new Date().getTime();
            if (currentTime>=sunrise && currentTime<sunset){
                icon = "&#xf00d";
            }
            else {
                icon = "&%xf02e;";
            }

        }
        else{
            switch(id){
                case 2 : icon = "&#xf01e;";
                break;
                case 3 : icon = "&¤xf01c";
                break;
                case 5 : icon = "&#xf019;";
                break;
                case 6 : icon = "&#xf01b;";
                break;
                case 7 : icon = "&#xf014;";
                break;
                case 8 : icon = "&#xf013;";
                break;

            }
        }
        return icon;
    }
    public interface AsyncResponse{
        void processFinish(String output1, String output2, String output3, String output4, String output5, String output6, String output7, String output8);

    }
    public static class placeIdTask extends AsyncTask<String, Void, JSONObject> {

        public AsyncResponse delegate = null;
        public placeIdTask(AsyncResponse asyncResponse){
            delegate = asyncResponse;
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jsonWeather = null;
            try{
                jsonWeather = getWeatherJSON(params[0], params[1]);
            }
            catch (Exception e){
                Log.d("Error", "Ei saa JSON tulemusi",e);
            }
            return jsonWeather;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try{
                if (json != null){
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    DateFormat dateFormat = DateFormat.getDateInstance();
                    String city = json.getString("name".toUpperCase(Locale.US) + ", " + json.getJSONObject("sys").getString("country"));
                    String description = details.getString("description").toUpperCase(Locale.US);
                    String temperature = String.format("%.2f",main.getDouble("temp")) + "°";
                    String humidity = main.getString("humidity") + "%";
                    String pressure = main.getString("pressure") + "hPa";
                    String updatedOn = dateFormat.format(new Date(json.getLong("dt")*1000));
                    String iconText = setWeatherIcon
                }
            }
            catch (JSONException e){

            }
            super.onPostExecute(json);
        }
    }
    public static JSONObject getWeatherJSON(String lat, String lon){
        try{

        }
        catch (Exception e){
            return null;
        }
    }

}
