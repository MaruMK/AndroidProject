package net.caesarlegion.drugimpact;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import net.caesarlegion.drugimpact.Model.OnResponseListener;
import net.caesarlegion.drugimpact.Model.PostExperience;

import org.json.JSONException;
import org.json.JSONObject;

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

public class CreateExperienceTask extends AsyncTask<PostExperience,Void,String> {

   private static final String PREFIX = "http://10.0.2.2:9999";

    @Override
    protected String doInBackground(PostExperience... postExp) {
        PostExperience PostInfo = postExp[0];
        try
        {
            URL url = new URL(PREFIX + "/experience");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            Log.d("TTTTTTTTTTTTTTTTTTTTTT",url.toString());
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "multipart/form-data");
            con.setRequestMethod("POST");

            PrintStream out = new PrintStream(con.getOutputStream());
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

            Log.d("LLLLLLLLLLLLLLLLLLLL",gson.toJson(PostInfo));
            //String str_JSON="{\"fields\": {\"first name\": [{\"value\": \"Jack\",\"modifier\": \"\"}],\"last name\": [{\"value\": \"Daniels\",\"modifier\": \"\"}],\"phone\": [{\"modifier\": \"work\",\"value\": \"123123123\"},{\"modifier\": \"work\",\"value\": \"2222\"}]},\"type\": \"person\",\"tags\": \"test\"}";
            out.println(gson.toJson(PostInfo));
            out.close();


            int code = con.getResponseCode();
            String piss = "LOL "+code;

            if(con.getResponseCode() != 201)
            {
                Log.d("BBBBBBBBBAAAAAAAADDDD",piss);
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
