package net.caesarlegion.drugimpact.Fragments;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import net.caesarlegion.drugimpact.ExperienceData;
import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivity;
import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivityAdapter;
import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivity;
import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivityAdapter;
import net.caesarlegion.drugimpact.R;
import net.caesarlegion.drugimpact.RecentActivityData;

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
        View rootView = inflater.inflate(R.layout.fragment_browse_exps, container, false);

        ListView experienceList = rootView.findViewById(R.id.listView_experiences);
        List<ExperienceActivity> data = ExperienceData.getData();
        ExperienceActivityAdapter adapter = new ExperienceActivityAdapter(getContext());
        adapter.addAll(data);
        experienceList.setAdapter(adapter);
        return rootView;
    }
}
