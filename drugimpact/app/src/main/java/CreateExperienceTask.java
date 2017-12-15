import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Connor on 2017-12-15.
 */

public class CreateExperienceTask extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... urls) {
        String urlStr = urls[0];
        String line="";
        StringBuilder result = new StringBuilder(100);
        try
        {
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("PUT");
            con.setDoOutput(true);

            PrintStream out = new PrintStream(con.getOutputStream());
            out.println();
            out.close();

            if(con.getResponseCode() != 200)
            {
                throw new IOException("Not Created:");
            }

            return con.getHeaderField("Location");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
