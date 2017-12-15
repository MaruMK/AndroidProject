package net.caesarlegion.drugimpact.Fragments;

//Maxime Lachance

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import net.caesarlegion.drugimpact.EmergencyText;
import net.caesarlegion.drugimpact.ListAdapters.HistoryAdapter.HistoryAdapter;
import net.caesarlegion.drugimpact.MainActivity;
import net.caesarlegion.drugimpact.Model.DatabaseException;
import net.caesarlegion.drugimpact.Model.Drug;
import net.caesarlegion.drugimpact.Model.DrugSafety;
import net.caesarlegion.drugimpact.Model.DrugSafetyData;
import net.caesarlegion.drugimpact.Model.History;
import net.caesarlegion.drugimpact.Model.HistoryDatabaseHandler;
import net.caesarlegion.drugimpact.Model.onDrugClickedListener;
import net.caesarlegion.drugimpact.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RemindersFragment extends Fragment {

    private View root;

    public static History elementToDelete;

    //Whenever a substance is on countdown till substance, this variable will
    //hold the timer with the highest value
    public static CountDownTimer currentTimer;
    public static List<CountDownTimer> timersList = new ArrayList<>();

    //This database will hold the history values. It is (will be) encrypted
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

        return root;
    }

    //=======================================================================================================================================
    //This function will be called whenever the ok button is pressed to add a substance
    public void setOkBtn() {
        Button button = root.findViewById(R.id.ok_btn);
        final Spinner spinner = root.findViewById(R.id.substances_spinner);
        historyDatabase = new HistoryDatabaseHandler(getContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText amountBox = root.findViewById(R.id.amt_consumed_box);
                EditText concentrationBox = root.findViewById(R.id.concentration_box);
                try {
                    History element = new History();
                    List<History> historyData = historyDatabase.historyTable.readAll();
                    if (amountBox.getText().toString() != null && !amountBox.getText().toString().isEmpty()) {
                        Double amount = Double.parseDouble(amountBox.getText().toString());
                        switch (spinner.getSelectedItem().toString()) {
                            case "Alcohol":
                                if (concentrationBox.getText().toString() != null && !concentrationBox.getText().toString().isEmpty()) {
                                    Double concentration = Double.parseDouble(concentrationBox.getText().toString());
                                    element = getHistoryByDrugId(DrugSafetyData.ALCOHOL_ID);
                                    //If the drug is already within the database, don't create a new one, update the old one instead
                                    if( element.getReminderId() != -1) {
                                        element.setAmount(element.getAmount() + DrugSafetyData.ConvertAlcoholVolumeToDrinks(amount, concentration));
                                        element.setTimeOfConsumption(new Date());
                                        historyDatabase.historyTable.update(element);
                                    }
                                    else{
                                        element = new History(DrugSafetyData.ALCOHOL_ID, DrugSafetyData.ConvertAlcoholVolumeToDrinks(amount, concentration), new Date());
                                        historyDatabase.historyTable.create(element);
                                    }
                                    //historyData.add(new History(DrugSafetyData.ALCOHOL_ID, DrugSafetyData.ConvertAlcoholVolumeToDrinks(amount, concentration), new Date()));
                                } else {
                                    Toast.makeText(getContext(), "Please enter a concentration (0-100%)", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case "Caffeine":
                                Spinner caffeineSpinner = root.findViewById(R.id.caffeine_method_spinner);
                                element = getHistoryByDrugId(DrugSafetyData.CAFFEINE_ID);
                                if( element.getReminderId() != -1) {
                                    element.setAmount(element.getAmount() + DrugSafetyData.GetCaffeineInMg(caffeineSpinner.getSelectedItem().toString(), amount));
                                    element.setTimeOfConsumption(new Date());
                                    historyDatabase.historyTable.update(element);
                                }
                                else{
                                    element = new History(DrugSafetyData.CAFFEINE_ID, DrugSafetyData.GetCaffeineInMg(caffeineSpinner.getSelectedItem().toString(), amount), new Date());
                                    historyDatabase.historyTable.create(element);
                                }
                                break;
                            case "Nicotine":
                                element = getHistoryByDrugId(DrugSafetyData.NICOTINE_ID);
                                if( element.getReminderId() != -1) {
                                    element.setAmount(element.getAmount() + amount);
                                    element.setTimeOfConsumption(new Date());
                                    historyDatabase.historyTable.update(element);
                                }
                                else{
                                    element = new History(DrugSafetyData.NICOTINE_ID, amount, new Date());
                                    historyDatabase.historyTable.create(element);
                                }
                                break;
                        }
                        DrugSafety ds = DrugSafetyData.FindSafetyBracket(element.getDrugId(), element.getAmount(), DrugSafetyData.WEIGHT_FOR_TESTING);
                        if(ds.getWarningLevel() == DrugSafetyData.WARNING){
                            Toast.makeText(getContext(), ds.getWarningMessage(), Toast.LENGTH_LONG).show();
                        }
                        //If the warning is of lethal level, pop up an alert box offering to contact the emergency contact
                        else if(ds.getWarningLevel() == DrugSafetyData.LETHAL){
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                            builder1.setMessage(ds.getWarningMessage()+", Would you like to send an emergency message to your preset contact?");
                            builder1.setCancelable(true);

                            builder1.setPositiveButton(
                                    "Yes",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            startActivity(EmergencyText.SendText(getContext(), DrugSafetyData.EMERGENCY_NUMBER, DrugSafetyData.EMERGENCY_MESSAGE));
                                        }
                                    });

                            builder1.setNegativeButton(
                                    "No",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                            AlertDialog alert11 = builder1.create();
                            alert11.show();
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
    public void updateTime() {
        final TextView timeTxt = root.findViewById(R.id.time_to_sober_txt);

        int biggestTime = 0;
        History biggestSubstance = new History();
        try {

            //Determine the substance with the biggest countdown timer
            for (History h :
                    historyDatabase.historyTable.readAll()) {
                int time = DrugSafetyData.CalculateSecondsTillSober(h);
                if (time > biggestTime) {
                    biggestTime = time;
                    biggestSubstance = h;
                }
            }

            //Put background timers for all the other substances
            for (CountDownTimer t:
                    timersList) {
                t.cancel();
            }
            timersList = new ArrayList<>();
            for(final History h:
                historyDatabase.historyTable.readAll()){
                if(h.getDrugId() != biggestSubstance.getDrugId()) {
                    timersList.add(new CountDownTimer(DrugSafetyData.CalculateSecondsTillSober(h) * 1000, 1000) {
                        public void onTick(long millisUntiFinished) {
                            h.setAmount(h.getAmount() - DrugSafetyData.FindSafetyBracket(h.getDrugId(), h.getAmount(), DrugSafetyData.WEIGHT_FOR_TESTING).getMetabolizationRate()/60/60);
                            try {
                                historyDatabase.historyTable.update(h);
                            } catch (DatabaseException e) {
                            } catch (NullPointerException e) {
                            }
                        }
                        public void onFinish() {
                            onSubstanceSober(h);
                        }
                    }.start());
                }
            }

            if(biggestTime == 0){
                timeTxt.setText("00h00m00");
            }
            else{
                final History finalBiggestSubstance = biggestSubstance;

                if(currentTimer != null){
                    currentTimer.cancel();
                }
                currentTimer = new CountDownTimer(biggestTime*1000, 1000){
                    public void onTick(long millisUntiFinished) {
                        finalBiggestSubstance.setAmount(finalBiggestSubstance.getAmount() - DrugSafetyData.FindSafetyBracket(finalBiggestSubstance.getDrugId(), finalBiggestSubstance.getAmount(), DrugSafetyData.WEIGHT_FOR_TESTING).getMetabolizationRate()/60/60);
                        int hours = (int) millisUntiFinished / 3600000;
                        millisUntiFinished %= 3600000;
                        int minutes = (int) millisUntiFinished / 60000;
                        millisUntiFinished %= 60000;
                        int seconds = (int) millisUntiFinished / 1000;
                        timeTxt.setText((String.format("%02d", hours)) + "h" + (String.format("%02d", minutes)) + "m" + (String.format("%02d", seconds)));

                        try {
                            historyDatabase.historyTable.update(finalBiggestSubstance);
                            refreshAdapter();
                        }
                        catch(DatabaseException e){Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();}
                        catch ( NullPointerException e){}
                    }
                    public void onFinish(){
                        timeTxt.setText("00h00m00");
                        onSubstanceSober(finalBiggestSubstance);
                    }
                }.start();
            }
        }
        catch(DatabaseException e){}
    }

    //This function will send a push notification to the user
    public void onSubstanceSober(History h){
        try {
            //First off, once the substance has reached its' time till sober, we remove the substance from the database
            historyDatabase.historyTable.delete(h);
            refreshAdapter();
        }
        catch (DatabaseException e){}
        catch (NullPointerException e){}

        if(getContext() != null) {
            Toast.makeText(getContext(), "Now sober from " + DrugSafetyData.GetDrugById(h.getDrugId()).getName(), Toast.LENGTH_SHORT).show();
        }
        else {
            //TODO: IMPLEMENT PUSH NOTIFICATIONS HERE---------------------------------------------------------------------------------------------------------------------------------
            Log.d("Push", "This should be a push notif");
        }
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
    }

    //When the remove button is pressed for a particular drug, we refresh with the new list
    public onDrugClickedListener adapterListener = new onDrugClickedListener() {
        @Override
        public void onDrugClicked() {
            try {
                historyDatabase.historyTable.delete(elementToDelete);
                updateTime();
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
        spinner.setSelection(0);

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
                else if (text.getText() == "Nicotine"){
                    //When something else is selected, hide all the specific boxes
                    root.findViewById(R.id.concentration_box).setVisibility(View.GONE);
                    root.findViewById(R.id.caffeine_method_spinner).setVisibility(View.GONE);
                    //Change to hint to indicate that the amount will be in Mililliters
                    TextView amount = root.findViewById(R.id.amt_consumed_box);
                    amount.setHint("Amount (Cigarettes)");
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
    //This function will allow us to search the database for any given drug. The exact element will then be returned. If no match is found,
    //the returned item will have a reminderId of -1
    public History getHistoryByDrugId(long id){
        try {
            List<History> hlist = historyDatabase.historyTable.readAll();
            for(History h:
                    hlist){
                if(h.DrugId == id) {
                    return h;
                }
            }
        } catch (DatabaseException e){}
        return new History();
    }
    //=======================================================================================================================================

}
