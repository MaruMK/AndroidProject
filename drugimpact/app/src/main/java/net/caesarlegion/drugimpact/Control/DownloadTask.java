package net.caesarlegion.drugimpact.Control;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Maru on 2017-12-13.
 */

public class DownloadTask extends AsyncTask<String, Void , String> {

    private OnResponseListener<String> listener;

    public void setOnResponseListener(OnResponseListener<String> listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls) {
        String urlStr = urls[0];
        String parsedString = "";

        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            if(connection.getResponseCode() != 200)
                throw new IOException("Not found");


            //Got from https://stackoverflow.com/questions/13196234/simple-parse-json-from-url-on-android-and-display-in-listview
            InputStream in = connection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(in, "utf-8"), 8);
            StringBuilder sBuilder = new StringBuilder();

            String line = null;

            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }

            in.close();

            return sBuilder.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String jsonStr) {
        if(listener != null)
            listener.onResponse(jsonStr);
    }
}
