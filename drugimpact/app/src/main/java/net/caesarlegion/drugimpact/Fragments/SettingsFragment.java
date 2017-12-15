package net.caesarlegion.drugimpact.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.caesarlegion.drugimpact.Control.OnUploadResponse;
import net.caesarlegion.drugimpact.Control.SendFeedbackTask;
import net.caesarlegion.drugimpact.MainActivity;
import net.caesarlegion.drugimpact.Model.DrugSafetyData;
import net.caesarlegion.drugimpact.Model.Feedback;
import net.caesarlegion.drugimpact.R;

/**
 * Created by Gabriel Charlebois on 2017-12-13.
 */

public class SettingsFragment extends Fragment {

    public View rootView;

    private String userId;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        //Get the user ID from the activity so we know who's the author of the feedback
        userId = getArguments().getString(MainActivity.params.USER_ID);

        //=======================================================================================================================================
        //Load the existing emergency info in the textboxes
        LoadEmergencyInfo();
        //Add the event listener for the save emergency info button
        SetEmergencyButtonListener();
        //=======================================================================================================================================
        SetFeedbackEditTextListner();
        SetFeedbackButtonListener();


        return rootView;
    }

    /**
     * Author: Gabriel Charlebois
     * Purpose: Enable and disable the send button depending on wheter or not the feedback is empty.
     */
    private void SetFeedbackEditTextListner() {
        //Get our UI elements
        EditText editText = rootView.findViewById(R.id.feedback_edit);
        final Button button = rootView.findViewById(R.id.feedback_send);

        //When the text is empty, disable the send button, otherwise enable it
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                button.setEnabled(charSequence.length() != 0 );
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }

    /**
     * Author: Gabriel Charlebois
     * Purpose: Send the user's feedback to the server
     */
    private void SetFeedbackButtonListener() {
        //Get our UI elements
        Button button = rootView.findViewById(R.id.feedback_send);
        final EditText editText = rootView.findViewById(R.id.feedback_edit);

        //Disable the send button by default
        button.setEnabled(false);

        //On click, send the data to the server.
        final String id = userId;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Disable further user input
                view.setEnabled(false);
                editText.setEnabled(false);

                //Send the authored (with userId) feedback entered to the server

                Feedback feedback = new Feedback(id, editText.getText().toString());
                SendFeedbackTask sendFeedbackTask = new SendFeedbackTask();
                sendFeedbackTask.setAddress(MainActivity.ADDRESS);
                editText.setText("");

                //Before sending, set the response to enable the user input and point
                //out that the feedback was posted correctly.
                sendFeedbackTask.setListener(new OnUploadResponse<String>() {
                    @Override
                    public void onUploadResponse(String s) {
                        editText.setEnabled(true);
                        //
                        Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
                    }
                });
                sendFeedbackTask.execute(feedback);
            }
        });
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
