package net.caesarlegion.drugimpact;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.caesarlegion.drugimpact.Model.Drug;
import net.caesarlegion.drugimpact.Model.PostExperience;
import net.caesarlegion.drugimpact.Model.User;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A placeholder fragment containing a simple view.
 */
public class CreateUserActivityFragment extends Fragment {

    public CreateUserActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_create_user, container, false);

        View button = root.findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText e = root.findViewById(R.id.editEmail);
                String email = e.getText().toString();
                EditText p = root.findViewById(R.id.editPassword);
                String pass = p.getText().toString();
                EditText d = root.findViewById(R.id.editDisplay);
                String display = d.getText().toString();
                EditText b = root.findViewById(R.id.editBirth);
                String dob = b.getText().toString();
                EditText w = root.findViewById(R.id.editWeight);
                String wei = w.getText().toString();
                int weight = 1;
                try {
                    weight = Integer.parseInt(wei);
                } catch(NumberFormatException nfe) {
                    System.out.println("Could not parse " + nfe);
                }


                User user = new User(2,email,pass,display,dob,weight);
                Send(user);
            }
        });

        return root;
    }

    public void Send(User user)
    {
        CreateUserTask createTask = new CreateUserTask();
        createTask.execute(user);
    }
}
