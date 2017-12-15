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

import net.caesarlegion.drugimpact.Control.DownloadTask;
import net.caesarlegion.drugimpact.Control.DrugListAdapter;
import net.caesarlegion.drugimpact.Control.OnResponseListener;
import net.caesarlegion.drugimpact.Model.Drug;
import net.caesarlegion.drugimpact.Model.Druglist;
import net.caesarlegion.drugimpact.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

        sortOptions = (Spinner) rootView.findViewById(R.id.sortBySpinner);
        drugList = rootView.findViewById(R.id.drugListView);
        searchTerm = rootView.findViewById(R.id.searchText);
        //drugListAdapter = new DrugListAdapter(getContext(), sampleDrugs);

        final List<Drug> drugListFromServer = new ArrayList<>();



        /*====== Getting List of Drugs From Server ========================================================*/
        DownloadTask downloadTask = new DownloadTask();

        downloadTask.setOnResponseListener(new OnResponseListener<String>() {
            @Override
            public void onResponse(String data) {
                try{
                    JSONArray jsonArray = new JSONArray(data);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        jsonObject = jsonObject.getJSONObject("_embedded");
                        drugListFromServer.add(Drug.parse(jsonObject.toString()));
                        Log.d("DEBUG_JSON",jsonObject.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        downloadTask.execute("localhost:9999/drug");
        /*=================================================================================================*/

        drugListAdapter = new DrugListAdapter(getContext(), drugListFromServer);

        refreshListView(sampleDrugs);

        searchTerm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                drugListAdapter.getFilter().filter(s.toString());
                for (Drug d : drugListAdapter.filteredDrugList)
                    Log.d("DEBUG_FILTERED_LIST",d.getName());
                refreshListView(drugListAdapter.filteredDrugList);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



        return rootView;
    }

    private void refreshListView(List<Drug> data) {
        DrugListAdapter newAdapter = new DrugListAdapter(getContext());
        newAdapter.addAll(data);
        //Fill list with the adapter's content
        drugList.setAdapter(newAdapter);

        for (Drug d : drugListAdapter.filteredDrugList)
            Log.d("DEBUG_REFRESH_LIST",d.getName());

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
