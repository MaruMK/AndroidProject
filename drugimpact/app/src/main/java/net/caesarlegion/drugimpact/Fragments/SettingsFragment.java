package net.caesarlegion.drugimpact.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.caesarlegion.drugimpact.Model.DrugSafetyData;
import net.caesarlegion.drugimpact.R;

/**
 * Created by Scowl Gulch on 2017-12-13.
 */

public class SettingsFragment extends Fragment {

    public View rootView;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        //=======================================================================================================================================
        //Load the existing emergency info in the textboxes
        LoadEmergencyInfo();
        //Add the event listener for the save emergency info button
        SetEmergencyButtonListener();
        //=======================================================================================================================================

        return rootView;
    }

    //=======================================================================================================================================
    //Maxime Lachance
    //Add the event listener for the save emergency info button
    public void SetEmergencyButtonListener(){
        Button button = rootView.findViewById(R.id.save_emergency_info_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveEmergencyInfo();
            }
        });
    }
    //This function gathers the info from the textboxes, and if the
    //info is valid, it will save the info in DrugSafetyData
    public void SaveEmergencyInfo() {
        EditText phoneBox = rootView.findViewById(R.id.emergency_phone_num_box);
        EditText messageBox = rootView.findViewById(R.id.emergency_message_box);
        if (phoneBox.toString() != null && !phoneBox.getText().toString().isEmpty()) {
            if (messageBox.toString() != null && !messageBox.getText().toString().isEmpty()) {
                DrugSafetyData.EMERGENCY_NUMBER = phoneBox.getText().toString();
                DrugSafetyData.EMERGENCY_MESSAGE = messageBox.getText().toString();
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(), "No message entered", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "No phone number entered", Toast.LENGTH_SHORT).show();
        }
    }
    //This function will load the previously saved emergency info from DrugSafetyData
    //and fill the editBoxes with it
    public void LoadEmergencyInfo(){
        EditText phoneBox = rootView.findViewById(R.id.emergency_phone_num_box);
        EditText messageBox = rootView.findViewById(R.id.emergency_message_box);
        phoneBox.setText(DrugSafetyData.EMERGENCY_NUMBER);
        messageBox.setText(DrugSafetyData.EMERGENCY_MESSAGE);
    }
    //=======================================================================================================================================

}
