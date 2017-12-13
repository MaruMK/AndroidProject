package net.caesarlegion.drugimpact.Fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.caesarlegion.drugimpact.Control.OnDownloadedListener;
import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivity;
import net.caesarlegion.drugimpact.ListAdapters.RecentActivityAdapter.RecentActivityAdapter;
import net.caesarlegion.drugimpact.R;
import net.caesarlegion.drugimpact.RecentActivityData;

import java.util.List;

/**
 * Created by Gabriel Charlebois on 2017-11-26.
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
        final ListView listViewRecent = rootView.findViewById(R.id.listView_recent);
        final RecentActivityAdapter adapter = new RecentActivityAdapter(getContext());
        RecentActivityData.getServerData(new OnDownloadedListener<String>() {
            @Override
            public void onDownloaded(String s) {
                //TODO: Covert server output to recent stuff


                List<RecentActivity> recent = RecentActivityData.parseServerDataToList(s);
                //adapter.addAll(recent);
                //listViewRecent.setAdapter(adapter);
                Context context = getContext();
                CharSequence text = s;
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });



        return rootView;
    }
}
