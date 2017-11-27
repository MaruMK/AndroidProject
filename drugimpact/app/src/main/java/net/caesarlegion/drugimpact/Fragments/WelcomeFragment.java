package net.caesarlegion.drugimpact.Fragments;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivity;
import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivityAdapter;
import net.caesarlegion.drugimpact.R;
import net.caesarlegion.drugimpact.RecentActivityData;

import java.util.List;

/**
 * Created by Scowl Gulch on 2017-11-26.
 */

public class WelcomeFragment extends Fragment {
    public WelcomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        //Set the warning
        TextView textViewWarning = rootView.findViewById(R.id.textView_welcome_warning);
        String text = "<font color='red'><u><b>" + getString(R.string.welcome_warning_preface) + "</b></u></font> "+ getString(R.string.welcome_warning_text);
        textViewWarning.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);

        //Fill the list of recent activities
        ListView listViewRecent = rootView.findViewById(R.id.listView_recent);
        List<RecentActivity> data = RecentActivityData.getData();
        RecentActivityAdapter adapter = new RecentActivityAdapter(getContext());
        adapter.addAll(data);
        listViewRecent.setAdapter(adapter);
        return rootView;
    }
}
