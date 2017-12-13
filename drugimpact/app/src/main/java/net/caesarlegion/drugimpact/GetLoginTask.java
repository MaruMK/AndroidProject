package net.caesarlegion.drugimpact;

/**
 * Created by Connor on 2017-12-13.
 */

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import net.caesarlegion.drugimpact.Model.OnResponseListener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by 1555220 on 2017-11-06.
 */

class GetLoginTask extends AsyncTask<String,Void,String>{

    private OnResponseListener<String> listener;

    public void setOnResponseListener(OnResponseListener<String> listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls) {
        Log.d("aaaaaaaaaaaaaaaaaaaaa","testBad");
        String urlStr = urls[0];
        String line="";
        StringBuilder result = new StringBuilder(100);
        try
        {
            Log.d("aaaaaaaaaaaaaaaaaaaaa","IN THE TRY");
            Log.d("aaaaaaaaaaaaaaaaaaaaa",urlStr);
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            Log.d("aaaaaaaaaaaaaaaaaaaaa","URL CREATED");
            /*if(con.getResponseCode() != 200)
            {
                Log.d("aaaaaaaaaaaaaaaaaaaaa","SOMETHING WENT WRONG");
                throw new IOException("Not Found:");
            }*/
            Log.d("aaaaaaaaaaaaaaaaaaaaa","AFTER IO EXCEPTION");
            BufferedReader read = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while((line = read.readLine()) != null)
            {
                result.append(line);
            }
            Log.d("AAAAAAAAAAAAAAAAAAA",result.toString());
            return result.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if(listener != null)
        {
            listener.onResponse(s);
        }
    }
}