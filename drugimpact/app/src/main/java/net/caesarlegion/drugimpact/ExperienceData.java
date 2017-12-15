package net.caesarlegion.drugimpact;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivity;
import net.caesarlegion.drugimpact.Model.OnResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 1555220 on 2017-12-04.
 */

public class ExperienceData extends Application{
    public static OnResponseListener listener = new OnResponseListener<String>() {
        @Override
        public void onResponse(String data) {

            try {
                JSONObject info = new JSONObject(data);
                info = info.getJSONObject("_embedded");
                JSONArray arr = info.getJSONArray("experience");

                for(int i = 0; i < arr.length();i++)
                {
                    JSONObject item = new JSONObject(arr.getString(i));
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    };


    public static List<ExperienceActivity> getData(String dataSent) {
        List<ExperienceActivity> data = new ArrayList<>();
        try {
            JSONObject info = new JSONObject(dataSent);
            info = info.getJSONObject("_embedded");
            JSONArray arr = info.getJSONArray("experience");

            for(int i = 0; i < arr.length();i++)
            {
                JSONObject item = new JSONObject(arr.getString(i));
                String title = item.getString("title");
                String drug1 = item.getString("drug1");
                String drug2 = item.getString("drug2");
                String content = item.getString("content");
                data.add(new ExperienceActivity(title,drug1,drug2,new Date()));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return data;
    }


    public static void GetFromServer(OnResponseListener<String> onResponseListener)
    {
        GetLoginTask loginTask = new GetLoginTask();
        loginTask.setOnResponseListener(onResponseListener);

        LoginApplication loginApp = new LoginApplication();
        loginTask.execute(loginApp.PREFIX+"/experience");
    }
}
