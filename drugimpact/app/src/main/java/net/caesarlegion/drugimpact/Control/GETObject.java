package net.caesarlegion.drugimpact.Control;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Gabriel Charlebois on 2017-12-13.
 */

public class GETObject extends AsyncTask<String, Void, String> {


    private static final int BUFFER_SIZE = 1024;



    private OnDownloadedListener<String> listener;
    public void setListener(OnDownloadedListener<String> listener) {
        this.listener = listener;
    }
    @Override
    protected String doInBackground(String... urls) {


        String urlStr = urls[0];

        try {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");

            if (con.getResponseCode() != 200)
                throw new IOException("Not found!");

            BufferedInputStream in = new BufferedInputStream(con.getInputStream(), BUFFER_SIZE);

            ByteArrayOutputStream bytes= new ByteArrayOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(bytes);

            int i;
            while((i = in.read()) >= 0)
                out.write(i);

            in.close();
            out.close();

            return bytes.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if(listener != null)
            listener.onDownloaded(s);
    }
}
