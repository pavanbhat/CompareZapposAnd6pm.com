package com.pavan.comparezap;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTasks extends AsyncTask<String, Void, String> {

    private String solution = "";
    private String [] finalString = new String[2];
    URL url;
    HttpURLConnection httpURLConnection = null;





    @Override
    protected String doInBackground(String... urls) {

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
            inputStream = httpURLConnection.getInputStream();reader = new InputStreamReader(inputStream);
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

        return solution;
    }

    @Override
    protected void onPostExecute(String solution) {
        super.onPostExecute(solution);

        try {
            JSONObject jsonObject = new JSONObject(solution);
            JSONArray productInfo = jsonObject.getJSONArray("results");

            StringBuffer zapposBuffer = new StringBuffer();
            for(int i = 0; i < productInfo.length();i++){
                JSONObject finObj = productInfo.getJSONObject(i);
                String price = finObj.getString("price");
                String productName = finObj.getString("productName");
                zapposBuffer.append(productName + " : " + price + "\n");
            }

            StringBuffer pmBuffer = new StringBuffer();
            for(int i = 0; i < productInfo.length();i++){
                JSONObject finObj = productInfo.getJSONObject(i);
                String price = finObj.getString("price");
                String productName = finObj.getString("productName");
                pmBuffer.append(productName + " : " + price + "\n");
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
            Log.i("doer",String.valueOf(1) );
            MainActivity.displayZapposResults.setText(zapposBuffer.toString());
//            MainActivity.temp.setText(" "+String.valueOf(temperatureInteger)+" degree celcius");
//            MainActivity.fc.setText(description);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
class Product{
    String brandName;
    String thumbnailImageUrl;
    long productId;
    String originalPrice;
    String price;
    String percentOff;
    String productUrl;
    String productName;


    Product(String brandName, String thumbnailImageUrl, long productId, String originalPrice, String price, String percentOff, String productUrl, String productName){
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
