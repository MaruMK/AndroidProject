package net.caesarlegion.drugimpact.Fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import net.caesarlegion.drugimpact.ExperienceData;
import net.caesarlegion.drugimpact.GetLoginTask;
import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivity;
import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivityAdapter;
import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivity;
import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivityAdapter;
import net.caesarlegion.drugimpact.LoginApplication;
import net.caesarlegion.drugimpact.MainActivity;
import net.caesarlegion.drugimpact.Model.OnResponseListener;
import net.caesarlegion.drugimpact.R;
import net.caesarlegion.drugimpact.RecentActivityData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Scowl Gulch on 2017-11-26.
 */

public class BrowseExperiencesFragment extends Fragment {
    final LoginApplication loginApp = new LoginApplication();
    public BrowseExperiencesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_browse_exps, container, false);

        ListView experienceList = root.findViewById(R.id.listView_experiences);
        List<ExperienceActivity> data = ExperienceData.getData();
        ExperienceActivityAdapter adapter = new ExperienceActivityAdapter(getContext());
        adapter.addAll(data);
        experienceList.setAdapter(adapter);

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
                        Log.d("aaaaaaaaaaaaa",item.getString("title"));
                    }
                    //Toast toast = Toast.makeText(getActivity().getApplicationContext(), data, Toast.LENGTH_SHORT);
                    //toast.show();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
        loginTask.execute(loginApp.PREFIX+"/experience");

        return root;
    }
}
