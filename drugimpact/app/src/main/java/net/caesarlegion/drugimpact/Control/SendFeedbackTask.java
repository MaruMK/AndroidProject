package net.caesarlegion.drugimpact.Control;

import android.os.AsyncTask;
import android.util.Log;

import net.caesarlegion.drugimpact.Model.Feedback;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gabriel Charlebois on 2017-12-13.
 * Purpose: Send feedback to the server and store it there.
 */

public class SendFeedbackTask extends AsyncTask<Feedback, Void, String> {

    //Declare some variables
    private OnUploadResponse<String> listener;
    private String address = "http://192.168.2.11:9999/feedback";


    //Setters...
    public void setListener(OnUploadResponse<String> listener) {
        this.listener = listener;
    }
    public void setAddress(String address) {this.address = address + "feedback";}

    /**
     * Purpose: Given feedback, send it to the server to store it there.
     * @param entries
     * @return
     */
    @Override
    protected String doInBackground(Feedback... entries) {
        Feedback feedback = entries[0];
        try {

            //Connect to the server
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            //Tell the server we're doing a POST request
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            //Send our request to the server
            PrintStream out = new PrintStream(con.getOutputStream());
            out.println(feedback.toJSON());
            out.close();
            //Check the result of our POST attempt
            if (con.getResponseCode() != 201)
                throw new IOException("Feedback could not be sent to server.");

            //Return a success string
            return con.getHeaderField("Location");


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Purpuse: To be trigger when the POST task is done.
     * @param location
     */
    @Override
    protected void onPostExecute(String location) {
        if(listener != null)
            listener.onUploadResponse(location);
    }
}
