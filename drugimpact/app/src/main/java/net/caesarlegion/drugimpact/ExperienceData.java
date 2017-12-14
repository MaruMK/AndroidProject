package net.caesarlegion.drugimpact;

import android.app.Application;
import android.util.Log;

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

public class ExperienceData{
    //public static String ENDME;
    public static List<ExperienceActivity> getData() {
        List<ExperienceActivity> data = new ArrayList<>();
        data.add(new ExperienceActivity("Timmy","Weed","This stuff was pretty good",new Date()));
        //data.add(new ExperienceActivity("Bill","Coco","We going back to the future",new Date()));
        //data.add(new ExperienceActivity("KABOOM","OXY CLEAN","WASH YOUR DETERGENT NOW",new Date()));
        GetFromServer();
        //Log.d("aaaaaaaaaaaaaaaa",ENDME);
        return data;
    }


    public static void GetFromServer()
    {
        GetLoginTask loginTask = new GetLoginTask();
        loginTask.setOnResponseListener(new OnResponseListener<String>() {
            @Override
            public void onResponse(String data) {

                try {
                    JSONObject info = new JSONObject(data);
                    info = info.getJSONObject("_embedded");
                    JSONArray arr = info.getJSONArray("experience");

                    for(int i = 0; i < arr.length();i++)
                    {
                        JSONObject item = new JSONObject(arr.getString(i));

                        String test = item.getString("title");
                        //ENDME = test;
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        LoginApplication loginApp = new LoginApplication();
        loginTask.execute(loginApp.PREFIX+"/experience");
    }
}
