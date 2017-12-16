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

public class GetLoginTask extends AsyncTask<String,Void,String>{

    private OnResponseListener<String> listener;

    public void setOnResponseListener(OnResponseListener<String> listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls) {
        String urlStr = urls[0];
        String line="";

        StringBuilder result = new StringBuilder(100);
        try
        {
            //Create a new url using and string and then use that url to connect to our server
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //In this case we use GET because we're retrieving form the server
            con.setRequestMethod("GET");
            if(con.getResponseCode() != 200)
            {
                throw new IOException("Not Found:");
            }
            BufferedReader read = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while((line = read.readLine()) != null)
            {
                result.append(line);
            }
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