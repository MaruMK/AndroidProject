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

import java.util.Comparator;
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

        refreshListView(sampleDrugs);

        return rootView;
    }

    private void refreshListView(List<Drug> data) {
        //Create a new adapter
        final DrugListAdapter drugListAdapter = new DrugListAdapter(getContext());

        //Populate the adapter
        drugListAdapter.addAll(data);
        //Fill list with the adapter's content
        drugList.setAdapter(drugListAdapter);

        // EventListener for sorting
        sortOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                switch (pos) {
                    case 0:
                        drugListAdapter.sort(new Comparator<Drug>() {
                            @Override
                            public int compare(Drug o1, Drug o2) {
                                return o1.getName().compareTo(o2.getName());
                            }
                        });
                        break;
                    case 1:
                        drugListAdapter.sort(new Comparator<Drug>() {
                            @Override
                            public int compare(Drug o1, Drug o2) {
                                return o2.getName().compareTo(o1.getName());
                            }
                        });
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
