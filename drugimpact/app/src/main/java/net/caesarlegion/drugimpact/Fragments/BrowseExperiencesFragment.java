package net.caesarlegion.drugimpact.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
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
import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivity;
import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivityAdapter;
import net.caesarlegion.drugimpact.LoginApplication;
import net.caesarlegion.drugimpact.R;


import java.util.List;

/**
 * Created by Scowl Gulch on 2017-11-26.
 */

public class BrowseExperiencesFragment extends Fragment {
    final LoginApplication loginApp = new LoginApplication();
    private Handler handler = new Handler();
    public View root2 = null;
    public BrowseExperiencesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_browse_exps, container, false);
        root2 = root;

        //Time handler stuff
        handler.postDelayed(runnable, 1000);




        ListView experienceList = root2.findViewById(R.id.listView_experiences);
        ExperienceData.GetFromServer();
        List<ExperienceActivity> data = ExperienceData.getData();
        ExperienceActivityAdapter adapter = new ExperienceActivityAdapter(getContext());
        adapter.addAll(data);
        experienceList.setAdapter(adapter);

        return root;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            ListView experienceList = root2.findViewById(R.id.listView_experiences);
            ExperienceData.GetFromServer();
            List<ExperienceActivity> data = ExperienceData.getData();
            ExperienceActivityAdapter adapter = new ExperienceActivityAdapter(getContext());
            adapter.addAll(data);
            experienceList.setAdapter(adapter);
            handler.postDelayed(this, 5000);
        }
    };


}
