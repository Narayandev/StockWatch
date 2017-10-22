package com.example.naray.stockwatch;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naray on 3/18/2017.
 */

public class AsyncLoaderTask extends AsyncTask<String,Void,String> {
    private static final String TAG = "AsyncLoaderTask";
    private MainActivity mainActivity;
    //Context context;
    //public firstAsyncResponse res = null;
    private firstAsyncResponse res;
    private final String stockURL = "http://stocksearchapi.com/api/"+"?api_key=1273d301d6bb948dbafbd63ed4606663afab88c3";
    private final String yourAPIKey = "1273d301d6bb948dbafbd63ed4606663afab88c3";

    ArrayList<Stock> jsonlist = new ArrayList<>();
    String usersearchText;

    /*public AsyncLoaderTask(MainActivity ma) {
        this.mainActivity = ma;
    }*/
    public AsyncLoaderTask(firstAsyncResponse activityContext){ this.res = activityContext; }

    //public AsyncLoaderTask(Context mContext){ this.context = mContext; }

    @Override
    protected void onPostExecute(String s) {
        res.processNewStock(jsonlist,usersearchText);
    }


    @Override
    protected String doInBackground(String... params) {
        usersearchText = params[0];

        Uri.Builder buildURL = Uri.parse(stockURL).buildUpon();
        //buildURL.appendQueryParameter("?api_key", yourAPIKey);
        buildURL.appendQueryParameter("search_text", params[0]);
        String urlToUse = buildURL.build().toString();
        Log.d(TAG, "doInBackground: " + urlToUse);

        StringBuilder sb = new StringBuilder();
        try {

            URL url = new URL(urlToUse);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream is = conn.getInputStream();
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(ir);

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


        try {
            JSONArray myJsonlist = new JSONArray(s);
            for (int i = 0; i < myJsonlist.length(); i++) {
                JSONObject jsonobjects = myJsonlist.getJSONObject(i);
                String symbol = jsonobjects.getString("company_symbol");
                String company = jsonobjects.getString("company_name");
                jsonlist.add(new Stock(symbol,company));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
