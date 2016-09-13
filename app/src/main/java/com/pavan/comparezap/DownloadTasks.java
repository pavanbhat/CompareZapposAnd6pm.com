package com.pavan.comparezap;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class DownloadTasks extends AsyncTask<String, Void, String[]> {

    private String solution = "";
    private String [] finalString = new String[2];
    private HashMap<Long, Product> zappos;
    private HashMap<Long, Product> pm;

    URL url;
    HttpURLConnection httpURLConnection = null;


    @Override
    protected String[] doInBackground(String... urls) {

        try {
            url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            int data = reader.read();
            while (data != -1){
                char current = (char) data;
                solution += current;
                data = reader.read();
            }

            finalString[0] = "";
            finalString[0] += solution;


            url = new URL(urls[1]);
            inputStream = httpURLConnection.getInputStream();
            reader = new InputStreamReader(inputStream);
            data = reader.read();
            while (data != -1){
                char current = (char) data;
                solution += current;
                data = reader.read();
            }

            finalString[1] = "";
            finalString[1] += solution;

        }catch (Exception e){
            e.printStackTrace();
        }

        return finalString;
    }

    @Override
    protected void onPostExecute(String[] solution) {
        super.onPostExecute(solution);

        try {
            JSONObject jsonObjectZappos = new JSONObject(solution[0]);
            JSONArray productInfoZappos = jsonObjectZappos.getJSONArray("results");
            zappos = new HashMap<Long, Product>();

            for(int i = 0; i < productInfoZappos.length();i++){
                JSONObject finObj = productInfoZappos.getJSONObject(i);
                String brandName = finObj.getString("brandName");
                String thumbnailImageUrl = finObj.getString("thumbnailImageUrl");
                Long productId = Long.parseLong(finObj.getString("productId"));
                String originalPrice = finObj.getString("originalPrice");
                int price = Integer.parseInt(finObj.getString("price").substring(1));
                String percentOff = finObj.getString("percentOff");
                String productUrl = finObj.getString("productUrl");
                String productName = finObj.getString("productName");
                zappos.put(productId,new Product(brandName, thumbnailImageUrl, productId, originalPrice, price, percentOff, productUrl, productName));
            }


            JSONObject jsonObjectPm = new JSONObject(solution[1]);
            JSONArray productInfoPm = jsonObjectPm.getJSONArray("results");
            pm = new HashMap<Long, Product>();

            for(int i = 0; i < productInfoPm.length();i++){
                JSONObject finObj = productInfoPm.getJSONObject(i);
                String brandName = finObj.getString("brandName");
                String thumbnailImageUrl = finObj.getString("thumbnailImageUrl");
                Long productId = Long.parseLong(finObj.getString("productId"));
                String originalPrice = finObj.getString("originalPrice");
                int price = Integer.parseInt(finObj.getString("price").substring(1));
                String percentOff = finObj.getString("percentOff");
                String productUrl = finObj.getString("productUrl");
                String productName = finObj.getString("productName");
                pm.put(productId,new Product(brandName, thumbnailImageUrl, productId, originalPrice, price, percentOff, productUrl, productName));

            }
//            JSONObject productInfo = new JSONObject(jsonObject.getString("results"));
//            JSONObject forecast = new JSONObject(jsonObject.getString("weather"));
//            String description = forecast.getString("description");
//            JSONArray jsonArray = new JSONArray("weather");

//            JSONObject weatherData = new JSONObject(jsonObject.getString("weather"));
//            String weather = String.valueOf(jsonArray.get(2));

            /*Log.i("length check",productInfo.length()+"");
            Double temperature = Double.parseDouble(productInfo.getString("temp"));
            int temperatureInteger = (int) (temperature - 273.15);
            String placeName = jsonObject.getString("name");*/
//            Log.i("doer",String.valueOf(1) );
            DisplayActivity.zappos = this.zappos;
            DisplayActivity.pm = this.pm;
//            MainActivity.displayZapposResults.setText(zapposBuffer.toString());
//            MainActivity.temp.setText(" "+String.valueOf(temperatureInteger)+" degree celcius");
//            MainActivity.fc.setText(description);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
class Product{
    String brandName;
    String thumbnailImageUrl;
    long productId;
    String originalPrice;
    int price;
    String percentOff;
    String productUrl;
    String productName;


    Product(String brandName, String thumbnailImageUrl, long productId, String originalPrice, int price, String percentOff, String productUrl, String productName){
        this.brandName = brandName;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.productId = productId;
        this.originalPrice = originalPrice;
        this.price = price;
        this.percentOff = percentOff;
        this.productUrl = productUrl;
        this.productName = productName;
    }
}
