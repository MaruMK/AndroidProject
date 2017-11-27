package net.caesarlegion.drugimpact.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import net.caesarlegion.drugimpact.Control.DrugListAdapter;
import net.caesarlegion.drugimpact.Model.Drug;
import net.caesarlegion.drugimpact.Model.Druglist;
import net.caesarlegion.drugimpact.R;

import java.util.List;

/**
 * Created by Scowl Gulch on 2017-11-26.
 * Implemented by Mamoru-Maru Kajifusa
 */

public class BrowseDrugsFragment extends Fragment {
    private Spinner sortOptions;
    private TextView searchTerm;
    private ListView drugList;

    //sample data
    List<Drug> sampleDrugs = Druglist.getData();


    public BrowseDrugsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse_drugs, container, false);

        sortOptions = (Spinner) rootView.findViewById(R.id.sortBySpinner);
        drugList = rootView.findViewById(R.id.drugListView);

        // Initial display list
        DrugListAdapter drugListAdapter = new DrugListAdapter(getContext());
        drugListAdapter.addAll(sampleDrugs);
        drugList.setAdapter(drugListAdapter);

        // EventListener for sorting
        sortOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String sortingChoice = (String) parent.getItemAtPosition(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rootView;
    }

}
