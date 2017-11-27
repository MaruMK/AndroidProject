package net.caesarlegion.drugimpact.Fragments;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.caesarlegion.drugimpact.Control.CustomDrugReminderAdapter;
import net.caesarlegion.drugimpact.Model.onDrugClickedListener;
import net.caesarlegion.drugimpact.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Comments for this part are a little out of touch since it was based off of the notes application,
 * TODO: Fix the comments to make them more relevant
 */

public class RemindersFragment extends Fragment {

    private View root;
    private String[] subsArray = new String[]{"Alcohol", "Potatoes", "Salad"};
    private ArrayList<String> sTakenArray = new ArrayList<String>(Arrays.asList(new String[]{"Salad"}));


    public onDrugClickedListener adapterListener = new onDrugClickedListener() {
        @Override
        public void onDrugClicked() {
            Toast.makeText(getContext(), this.toString(), Toast.LENGTH_SHORT).show();
            refreshAdapter();
        }
    };

    public RemindersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_reminders, container, false);

        setOkBtn();

        fillSpinner();

        setCustomAdapter();

        return root;
    }

    //=======================================================================================================================================
    //This function will update the listview to matche the database
    public void setOkBtn() {
        Button button = root.findViewById(R.id.ok_btn);
        final Spinner spinner = root.findViewById(R.id.substances_spinner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sTakenArray.add(spinner.getSelectedItem().toString());
                updateTime();
                refreshAdapter();
            }
        });
    }
    //=======================================================================================================================================

    //=======================================================================================================================================
    //This function will update the listview to matche the database
    public void updateTime() {
        TextView timeTxt = root.findViewById(R.id.time_to_sober_txt);
        int time = sTakenArray.size()*75;
        timeTxt.setText((time/60)+"h"+(time%60));

    }
    //=======================================================================================================================================

    //=======================================================================================================================================
    //This function sets our custom adapter for the listview to be filled, using data fetched from the test files.
    //This is also where the notes are assigned an id and stored in the database
    public void setCustomAdapter() {

        //Get the view element in which to add
        ListView drugsList = root.findViewById(R.id.drugs_list_view);


        //Get all the notes given by NoteData and gather them in a list
        // insert all seed data.

        CustomDrugReminderAdapter adapter = new CustomDrugReminderAdapter(getContext(), adapterListener);
        adapter.addAll(sTakenArray);

        drugsList.setAdapter(adapter);
    }
    //=======================================================================================================================================

    //=======================================================================================================================================
    //This function will update the listview to matche the database
    public void refreshAdapter() {
        //noteDatabase = new NoteDatabaseHandler(getContext());
        //Get the view element in which to add
        ListView drugList = root.findViewById(R.id.drugs_list_view);

        CustomDrugReminderAdapter adapter = new CustomDrugReminderAdapter(getContext(), adapterListener);

        //Insert random data for now, drugs from database later
        adapter.addAll(sTakenArray);

        //Toast.makeText(getContext(), "Yay", Toast.LENGTH_SHORT).show();
        drugList.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

    }
    //=======================================================================================================================================

    //=======================================================================================================================================
    //This function simply fills the sorting options spinner with
    //what we give it
    public void fillSpinner(){
        final Spinner spinner = root.findViewById(R.id.substances_spinner);

        //Set the options that will be displayed in the spinner
        List<String> spinnerArray = new ArrayList<String>();
        spinnerArray.addAll(new ArrayList<String>(Arrays.asList(subsArray)));

        spinner.setSelection(1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Set the event listener to sort the list
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                TextView text = (TextView) selectedItemView;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //Empty because in no case will nothing be selected
            }
        });
    }
    //=======================================================================================================================================
}
