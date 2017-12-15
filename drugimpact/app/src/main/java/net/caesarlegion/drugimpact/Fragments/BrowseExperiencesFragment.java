package net.caesarlegion.drugimpact.Fragments;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import net.caesarlegion.drugimpact.ExperienceData;
import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivity;
import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivityAdapter;
import net.caesarlegion.drugimpact.LoginApplication;
import net.caesarlegion.drugimpact.MainActivity;
import net.caesarlegion.drugimpact.Model.OnResponseListener;
import net.caesarlegion.drugimpact.PostExperienceActivity;
import net.caesarlegion.drugimpact.R;


import java.util.List;

/**
 * Created by Scowl Gulch on 2017-11-26.
 */

public class BrowseExperiencesFragment extends Fragment {
    public BrowseExperiencesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_browse_exps, container, false);
        final ExperienceActivityAdapter adapter = new ExperienceActivityAdapter(getContext());
        final ListView experienceList = root.findViewById(R.id.listView_experiences);

        ExperienceData.GetFromServer(new OnResponseListener<String>() {
            @Override
            public void onResponse(String data) {
                Log.d("GGGGGGGGGGGGGGGGGGGGG",data);
                List<ExperienceActivity> data2 = ExperienceData.getData(data);
                adapter.addAll(data2);
                experienceList.setAdapter(adapter);
            }
        });

        View button = root.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PostExperienceActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }

}
