package net.caesarlegion.drugimpact;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import net.caesarlegion.drugimpact.Model.OnResponseListener;
import net.caesarlegion.drugimpact.Model.OnUploadResponse;
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

    private OnUploadResponse<String> listener;
    private String address = "http://192.168.0.44:9999/experience";

    public void setListener(OnUploadResponse<String> listener) {
        this.listener = listener;
    }
    public void setAddress(String address) {this.address = address + "experience";}

    @Override
    protected String doInBackground(PostExperience... postExp) {
        PostExperience PostInfo = postExp[0];
        try
        {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            Log.d("TTTTTTTTTTTTTTTTTTTTTT",url.toString());
            //Tell the server we're doing a POST request
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            Gson builder = new GsonBuilder().create();
            PrintStream out = new PrintStream(con.getOutputStream());
            Log.d("SSSSSSSSSSSSSSSSSSSSSS",builder.toJson(PostInfo));
            out.println(builder.toJson(PostInfo));
            out.close();


            //int code = con.getResponseCode();
            //String piss = "LOL "+code;

            if(con.getResponseCode() != 201)
            {
                //Log.d("BBBBBBBBBAAAAAAAADDDD",piss);
                throw new IOException("Not Created:");
            }
            Log.d("DDDDDDDDDDDDDDDDD","done");
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
    @Override
    protected void onPostExecute(String location) {
        if(listener != null)
            listener.onUploadResponse(location);
    }
}
