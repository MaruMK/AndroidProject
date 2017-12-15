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

                URL url = null;
                try {
                    url = new URL("http://www.google.com");
                }
                catch(MalformedURLException e) {
                    Log.d("dddd","woops");
                }
                Drug drug = new Drug(2,"SpeedWeed","Lol",url);
                Send(drug);
            }
        });

        return root;
    }

    public void Send(Drug drug)
    {
        CreateUserTask createTask = new CreateUserTask();
        createTask.execute(drug);
    }
}
