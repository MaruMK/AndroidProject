package net.caesarlegion.drugimpact.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.caesarlegion.drugimpact.Control.CustomDrugReminderAdapter;
import net.caesarlegion.drugimpact.Model.Drug;
import net.caesarlegion.drugimpact.Model.DrugSafety;
import net.caesarlegion.drugimpact.Model.DrugSafetyData;
import net.caesarlegion.drugimpact.Model.History;
import net.caesarlegion.drugimpact.Model.onDrugClickedListener;
import net.caesarlegion.drugimpact.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Comments for this part are a little out of touch since it was based off of the notes application,
 * TODO: (WIP) Fix the comments to make them more relevant
 */

public class RemindersFragment extends Fragment {

    private View root;

    //This is the arraylist that will store all of the history data
    //TODO: (EXTRA) ENCRYPT THIS DATA)
    public static ArrayList<History> historyData = new ArrayList<>();

    public RemindersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_reminders, container, false);

        //Sets what to do when the ok button is pressed
        setOkBtn();

        //Fills the substance spinner with the knowndrugs from DrugSafetyData
        fillSpinner();

        //Sets the custom adapter (drug_reminder_listitem) to the listview for substances consumed
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
                EditText amountBox = root.findViewById(R.id.amt_consumed_box);
                EditText concentrationBox = root.findViewById(R.id.concentration_box);
                CheckBox reminder = root.findViewById(R.id.checkBox);

                if(amountBox.getText().toString() != null && !amountBox.getText().toString().isEmpty()) {
                    Double amount = Double.parseDouble(amountBox.getText().toString());
                    switch (spinner.getSelectedItem().toString()) {
                        case "Alcohol":
                            if (concentrationBox.getText().toString() != null && !concentrationBox.getText().toString().isEmpty()) {
                                Double concentration = Double.parseDouble(concentrationBox.getText().toString());
                            }
                            else{
                                Toast.makeText(getContext(), "Please enter a concentration (0-100%)", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        //TODO: ALL THE OTHER SUBSTANCES
                    }

                    updateTime();
                    refreshAdapter();
                }
                else{
                    Toast.makeText(getContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //=======================================================================================================================================

    //=======================================================================================================================================
    //This function will update the APPROXIMATE time until sober based on the history of substances consumed
    //TODO IMPLEMENT THE ACTUAL CALCULATIONS INSTEAD OF DUMMY VALUES
    public void updateTime() {
        TextView timeTxt = root.findViewById(R.id.time_to_sober_txt);
        int time = historyData.size()*75;
        timeTxt.setText((time/60)+"h"+(time%60));
    }

    //This function will send a push notification to the user
    public void onSubstanceSober(Drug drug){
        //TODO: IMPLEMENT PUSH NOTIFICATIONS HERE---------------------------------------------------------------------------------------------------------------------------------
    }
    //=======================================================================================================================================

    //=======================================================================================================================================
    //This function sets our custom adapter for the listview to be filled, using data fetched from the test files.
    //This is also where the notes are assigned an id and stored in the database
    public void setCustomAdapter() {

        //Get the view element in which to add
        ListView drugsList = root.findViewById(R.id.drugs_list_view);

        CustomDrugReminderAdapter adapter = new CustomDrugReminderAdapter(getContext(), adapterListener);
        //adapter.addAll(historyData);

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
        adapter.addAll(historyData);

        //Toast.makeText(getContext(), "Yay", Toast.LENGTH_SHORT).show();
        drugList.setAdapter(adapter);
        updateTime();
    }

    //When the remove button is pressed for a particular drug, we refresh with the new list
    public onDrugClickedListener adapterListener = new onDrugClickedListener() {
        @Override
        public void onDrugClicked() {
            refreshAdapter();
        }
    };
    //=======================================================================================================================================

    //=======================================================================================================================================
    //This function simply fills the spinner for the available substances. It also fills the spinner for the caffeine methods.
    public void fillSpinner(){
        final Spinner spinner = root.findViewById(R.id.substances_spinner);
        final Spinner caffeineSpinner = root.findViewById(R.id.caffeine_method_spinner);
        caffeineSpinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item ,DrugSafetyData.caffeineMethods));

        //Set the options that will be displayed in the spinner
        List<String> spinnerArray = new ArrayList<>();
        for (Drug substance: DrugSafetyData.knownDrugs) {
            spinnerArray.add(substance.getName());
        }

        //Set the first element as the default choice
        spinner.setSelection(1);

        //Set the adapter for the substances spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Set the event listener to sort the list
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                TextView text = (TextView) selectedItemView;
                if(text == null)
                    return;

                //The following section modifies the UI to reflect the substance selected in the spinner. For example, alcohol will have
                //concentration, caffeine will have different means of consumption (Soft drink, Coffee, Energy drinks, etc)
                //=======================================================================================================================================
                //This section makes changes to the ui based on the substance selected
                if(text.getText() == "Alcohol"){
                    //When alcohol is selected, let the user input the concentration
                    root.findViewById(R.id.concentration_box).setVisibility(View.VISIBLE);
                    TextView concentration = root.findViewById(R.id.concentration_box);
                    concentration.setHint("Concentration(%)");
                    //Hide the other boxes (such as caffeine method)
                    root.findViewById(R.id.caffeine_method_spinner).setVisibility(View.GONE);
                    //Change to hint to indicate that the amount will be in Mililliters
                    TextView amount = root.findViewById(R.id.amt_consumed_box);
                    amount.setHint("Amount (ml)");
                }
                else if(text.getText() == "Caffeine"){
                    //When caffeine is selected, let the user select a mean of input
                    root.findViewById(R.id.caffeine_method_spinner).setVisibility(View.VISIBLE);
                    //Hide the other boxes (such as alcohol concentration)
                    root.findViewById(R.id.concentration_box).setVisibility(View.GONE);
                    //Change to hint to indicate that the amount will be in Mililliters
                    TextView amount = root.findViewById(R.id.amt_consumed_box);
                    amount.setHint("Amount (ml)");
                }
                else if (text.getText() == "Nicotine - Cigarette"){
                    //When something else is selected, hide all the specific boxes
                    root.findViewById(R.id.concentration_box).setVisibility(View.GONE);
                    root.findViewById(R.id.caffeine_method_spinner).setVisibility(View.GONE);
                    //Change to hint to indicate that the amount will be in Mililliters
                    TextView amount = root.findViewById(R.id.amt_consumed_box);
                    amount.setHint("Amount (Cigarettes)");
                }
                else if (text.getText() == "Nicotine - Vaporizer"){
                    //When something else is selected, hide all the specific boxes
                    root.findViewById(R.id.concentration_box).setVisibility(View.VISIBLE);
                    TextView concentration = root.findViewById(R.id.concentration_box);
                    concentration.setHint("Concentration(mg/ml)");
                    root.findViewById(R.id.caffeine_method_spinner).setVisibility(View.GONE);
                    //Change to hint to indicate that the amount will be in Mililliters
                    TextView amount = root.findViewById(R.id.amt_consumed_box);
                    amount.setHint("Amount (ml)");
                }
                else{
                    //When something else is selected, hide all the specific boxes
                    root.findViewById(R.id.concentration_box).setVisibility(View.GONE);
                    root.findViewById(R.id.caffeine_method_spinner).setVisibility(View.GONE);
                    //Change to hint to indicate that the amount will be in Mililliters
                    TextView amount = root.findViewById(R.id.amt_consumed_box);
                    amount.setHint("Amount (Mg)");
                }
                //=======================================================================================================================================
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //Empty because in no case will nothing be selected
            }
        });
    }
    //=======================================================================================================================================
}
