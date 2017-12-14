package net.caesarlegion.drugimpact.Fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
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

import net.caesarlegion.drugimpact.ListAdapters.HistoryAdapter.HistoryAdapter;
import net.caesarlegion.drugimpact.Model.DatabaseException;
import net.caesarlegion.drugimpact.Model.Drug;
import net.caesarlegion.drugimpact.Model.DrugSafetyData;
import net.caesarlegion.drugimpact.Model.History;
import net.caesarlegion.drugimpact.Model.HistoryDatabaseHandler;
import net.caesarlegion.drugimpact.Model.onDrugClickedListener;
import net.caesarlegion.drugimpact.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Comments for this part are a little out of touch since it was based off of the notes application,
 * TODO: (WIP) Fix the comments to make them more relevant
 */

public class RemindersFragment extends Fragment {

    private View root;

    //This is the arraylist that will store all of the history data
    public static ArrayList<History> historyData = new ArrayList<>();
    public static History elementToDelete;

    public static CountDownTimer currentTimer;

    public HistoryDatabaseHandler historyDatabase = new HistoryDatabaseHandler(getContext());

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
        refreshAdapter();

        updateTime();

        Toast.makeText(getContext(), Double.toString(getHistoryByDrugId(historyData, DrugSafetyData.ALCOHOL_ID).getAmount()), Toast.LENGTH_LONG).show();

        return root;
    }

    //=======================================================================================================================================
    //This function will update the listview to matche the database
    public void setOkBtn() {
        Button button = root.findViewById(R.id.ok_btn);
        final Spinner spinner = root.findViewById(R.id.substances_spinner);
        historyDatabase = new HistoryDatabaseHandler(getContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText amountBox = root.findViewById(R.id.amt_consumed_box);
                EditText concentrationBox = root.findViewById(R.id.concentration_box);
                CheckBox reminder = root.findViewById(R.id.checkBox);
                try {
                    List<History> historyData = historyDatabase.historyTable.readAll();

                    if (amountBox.getText().toString() != null && !amountBox.getText().toString().isEmpty()) {
                        Double amount = Double.parseDouble(amountBox.getText().toString());
                        switch (spinner.getSelectedItem().toString()) {
                            case "Alcohol":
                                if (concentrationBox.getText().toString() != null && !concentrationBox.getText().toString().isEmpty()) {
                                    Double concentration = Double.parseDouble(concentrationBox.getText().toString());
                                    History existingElement = getHistoryByDrugId(historyData, DrugSafetyData.ALCOHOL_ID);
                                    //If the drug is already within the database, don't create a new one, update the old one instead
                                    if( existingElement.getReminderId() != -1) {
                                        History newElement = new History();
                                        newElement.setReminderId(existingElement.getReminderId());
                                        newElement.setDrugId(DrugSafetyData.ALCOHOL_ID);
                                        //Toast.makeText(getContext(), Double.toString(existingElement.getAmount()), Toast.LENGTH_LONG).show();
                                        newElement.setAmount(existingElement.getAmount() + DrugSafetyData.ConvertAlcoholVolumeToDrinks(amount, concentration));
                                        newElement.setTimeOfConsumption(new Date());
                                        historyDatabase.historyTable.update(newElement);
                                    }
                                    else{
                                        historyDatabase.historyTable.create(new History(DrugSafetyData.ALCOHOL_ID, DrugSafetyData.ConvertAlcoholVolumeToDrinks(amount, concentration), new Date()));
                                    }
                                    //historyData.add(new History(DrugSafetyData.ALCOHOL_ID, DrugSafetyData.ConvertAlcoholVolumeToDrinks(amount, concentration), new Date()));
                                } else {
                                    Toast.makeText(getContext(), "Please enter a concentration (0-100%)", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            //TODO: ALL THE OTHER SUBSTANCES
                        }

                        updateTime();
                        refreshAdapter();
                    } else {
                        Toast.makeText(getContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(DatabaseException e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //=======================================================================================================================================

    //=======================================================================================================================================
    //This function will update the APPROXIMATE time until sober based on the history of substances consumed
    //TODO IMPLEMENT THE ACTUAL CALCULATIONS INSTEAD OF DUMMY VALUES
    public void updateTime() {
        final TextView timeTxt = root.findViewById(R.id.time_to_sober_txt);

        int biggestTime = 0;
        History biggestSubstance = new History();
        try {
            for (History h :
                    historyDatabase.historyTable.readAll()) {
                int time = DrugSafetyData.CalculateSecondsTillSober(h);
                if (time > biggestTime) {
                    biggestTime = time;
                    biggestSubstance = h;
                }
            }
            if(biggestTime == 0){
                timeTxt.setText("00h00m00");
            }
        }
        catch(DatabaseException e){}

        final History finalBiggestSubstance = biggestSubstance;
        final int finalBiggestTime = biggestTime*1000;


        if(currentTimer != null){
            currentTimer.cancel();
        }
        currentTimer = new CountDownTimer(biggestTime*1000, 1000){
            public void onTick(long millisUntiFinished) {
                finalBiggestSubstance.setAmount(finalBiggestSubstance.getAmount() * (finalBiggestTime / millisUntiFinished));
                int hours = (int) millisUntiFinished / 3600000;
                millisUntiFinished %= 3600000;
                int minutes = (int) millisUntiFinished / 60000;
                millisUntiFinished %= 60000;
                int seconds = (int) millisUntiFinished / 1000;
                timeTxt.setText((hours) + "h" + (minutes) + "m" + (seconds));

                try {
                    historyDatabase.historyTable.update(finalBiggestSubstance);
                }
                catch(DatabaseException e){Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();}
            }
            public void onFinish(){
                onSubstanceSober(finalBiggestSubstance.DrugId);
            }
        }.start();
    }

    //This function will send a push notification to the user
    public void onSubstanceSober(long drugId){
        Toast.makeText(getContext(), "THIS WILL BE A PUSH NOTIFICATION", Toast.LENGTH_SHORT).show();
        //TODO: IMPLEMENT PUSH NOTIFICATIONS HERE---------------------------------------------------------------------------------------------------------------------------------
    }
    //=======================================================================================================================================

    //=======================================================================================================================================
    //This function sets our custom adapter for the listview to be filled, using data fetched from the test files.
    //This is also where the notes are assigned an id and stored in the database
    public void setCustomAdapter() {

        //Get the view element in which to add
        ListView drugsList = root.findViewById(R.id.drugs_list_view);

        HistoryAdapter adapter = new HistoryAdapter(getContext(), adapterListener);
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

        HistoryAdapter adapter = new HistoryAdapter(getContext(), adapterListener);

        try {
            //Insert random data for now, drugs from database later
            adapter.addAll(historyDatabase.historyTable.readAll());
        }
        catch (DatabaseException e){
            Toast.makeText(getContext(), "THIS WILL BE A PUSH NOTIFICATION", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(getContext(), "Yay", Toast.LENGTH_SHORT).show();
        drugList.setAdapter(adapter);
        updateTime();
    }

    //When the remove button is pressed for a particular drug, we refresh with the new list
    public onDrugClickedListener adapterListener = new onDrugClickedListener() {
        @Override
        public void onDrugClicked() {
            try {
                historyDatabase.historyTable.delete(elementToDelete);
                refreshAdapter();
            }
            catch (DatabaseException e){}
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

                if (text == null)
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

    //=======================================================================================================================================
    public History getHistoryByDrugId(List<History> hlist, long id){
        for(History h:
            hlist){
            if(h.DrugId == id) {
                return h;
            }
        }
        return new History();
    }
    //=======================================================================================================================================

}
