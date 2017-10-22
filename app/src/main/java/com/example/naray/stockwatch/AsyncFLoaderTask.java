package com.example.naray.stockwatch;

import android.app.Application;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by naray on 3/19/2017.
 */

public class AsyncFLoaderTask extends AsyncTask<String,Void,String> {
    private static final String TAG = "AsyncFLoaderTask";
    private MainActivity mainActivity;

    private final String finURL = "http://finance.google.com/finance/info?client=ig";
    Stock stockdata;
    //public secondAsyncResponse res2 = null;
    private secondAsyncResponse res2;
    /*public AsyncFLoaderTask(MainActivity main) {
        this.mainActivity = main;
    }*/
    public AsyncFLoaderTask(secondAsyncResponse activityContext){ this.res2 = activityContext;}

    @Override
    protected void onPostExecute(String s) {
        res2.addNewStock(stockdata);
    }
    String stockcompany;
    String stocksymbol;
    @Override
    protected String doInBackground(String... params) {

        stockcompany = params[1];
        stocksymbol = params[0];
        Uri.Builder buildURL = Uri.parse(finURL).buildUpon();
        buildURL.appendQueryParameter("q", params[0]);
        String urlToUse = buildURL.build().toString();
        Log.d(TAG, "doInBackground: " + urlToUse);

        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlToUse);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader((new InputStreamReader(is)));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

            Log.d(TAG, "doInBackground: " + sb.toString());

        } catch (Exception e) {
            Log.e(TAG, "doInBackground: ", e);
            return null;
        }

        parseJSON(sb.toString());

        return null;
    }

    private void parseJSON(String s) {

        String result = s.replace("// ["," [");
        try {
            JSONArray myJsonFlist = new JSONArray(result);

                JSONObject jsonobjects = myJsonFlist.getJSONObject(0);
                String ticker = jsonobjects.getString("t");
                if(ticker.equals(stocksymbol)) {
                    String lastTradePrice = jsonobjects.getString("l");
                    String priceChangeAmount = jsonobjects.getString("c");
                    String priceChangePercentage = jsonobjects.getString("cp");
                    stockdata = new Stock(stocksymbol,lastTradePrice, priceChangeAmount, priceChangePercentage,stockcompany);
                }
                else{
                   // Toast.makeText(getApplicationContext(), "Error downloading data ", Toast.LENGTH_SHORT);
                    return;
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
