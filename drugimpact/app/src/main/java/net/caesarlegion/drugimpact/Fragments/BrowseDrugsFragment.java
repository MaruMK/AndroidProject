package net.caesarlegion.drugimpact.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.caesarlegion.drugimpact.R;

/**
 * Created by Scowl Gulch on 2017-11-26.
 */

public class BrowseDrugsFragment extends Fragment {
    public BrowseDrugsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse_drugs, container, false);
        return rootView;
    }
}
