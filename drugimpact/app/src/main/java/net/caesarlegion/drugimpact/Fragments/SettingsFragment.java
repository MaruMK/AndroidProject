package net.caesarlegion.drugimpact.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.caesarlegion.drugimpact.R;

/**
 * Created by Scowl Gulch on 2017-12-13.
 */

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);



        return rootView;
    }
}
