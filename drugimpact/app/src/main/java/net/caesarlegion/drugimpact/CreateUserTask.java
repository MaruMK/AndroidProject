package net.caesarlegion.drugimpact;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import net.caesarlegion.drugimpact.Model.Drug;
import net.caesarlegion.drugimpact.Model.OnResponseListener;
import net.caesarlegion.drugimpact.Model.PostExperience;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
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

public class CreateUserTask extends AsyncTask<Drug,Void,String> {
    private static final String PREFIX = "http://10.0.2.2:9999";
    @Override
    protected String doInBackground(Drug... drugs) {
        Drug drug = drugs[0];
        try
        {
            URL url = new URL(PREFIX+"/drug");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            Log.d("TTTTTTTTTTTTTTTTTTTTTT",url.toString());
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            con.setRequestMethod("POST");

            /*PrintStream out = new PrintStream(con.getOutputStream());
            Gson gson = new GsonBuilder().create();
            String value = gson.toJson(drug);

            String ruv = "{'DrugId':2,'Name':'SpeedWeed','Unit':'Lol','url':'http://www.google.com'}";
            Log.d("WWWWWWWWWWWWWOOO",value);

            out.println(value);
            out.close();*/

            Gson gson = new GsonBuilder().create();
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(gson.toJson(drug));
            wr.flush();
            wr.close();



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
