package net.caesarlegion.drugimpact.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
    private DrugListAdapter drugListAdapter;

    //sample data
    List<Drug> sampleDrugs = Druglist.getData();


    public BrowseDrugsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse_drugs, container, false);

        sortOptions = rootView.findViewById(R.id.sortBySpinner);
        drugList = rootView.findViewById(R.id.drugListView);
        searchTerm = rootView.findViewById(R.id.searchText);
        drugListAdapter = new DrugListAdapter(getContext(), sampleDrugs);

        final DrugListAdapter dl = drugListAdapter;

        refreshListView(sampleDrugs);

        searchTerm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (dl != null) {
                    dl.getFilter().filter(s);
                    refreshListView(dl.filteredDrugList);
                }
                else {
                    Log.d("filter", "no filter available");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return rootView;
    }

    private void refreshListView(List<Drug> data) {
        drugListAdapter = new DrugListAdapter(getContext());
        drugListAdapter.addAll(data);
        //Fill list with the adapter's content
        drugList.setAdapter(drugListAdapter);

        // EventListener for sorting
        sortOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                final int ALPHABETICAL_ASC=0;
                final int ALPHABETICAL_DESC=1;

                switch (pos) {
                    case ALPHABETICAL_ASC:
                        drugListAdapter.sort(new Comparator<Drug>() {
                            @Override
                            public int compare(Drug o1, Drug o2) {
                                return o1.getName().compareTo(o2.getName());
                            }
                        });
                        break;
                    case ALPHABETICAL_DESC:
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
