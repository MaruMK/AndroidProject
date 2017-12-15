package net.caesarlegion.drugimpact;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivity;
import net.caesarlegion.drugimpact.Model.OnResponseListener;
import net.caesarlegion.drugimpact.Model.PostExperience;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class PostExperienceActivityFragment extends Fragment {

    public PostExperienceActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_post_experience, container, false);

        View button = root.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Title = root.findViewById(R.id.editText3);
                EditText Drug1 = root.findViewById(R.id.editText4);
                EditText Drug2 = root.findViewById(R.id.editText5);
                EditText Content = root.findViewById(R.id.editText6);

                String t = Title.getText().toString();
                String d1 = Drug1.getText().toString();
                String d2 = Drug2.getText().toString();
                String c = Content.getText().toString();

                int D1;
                try {
                    D1 = Integer.parseInt(d1);
                } catch(NumberFormatException nfe) {
                    D1 = 1;
                }
                int D2;
                try {
                    D2 = Integer.parseInt(d2);
                } catch(NumberFormatException nfe) {
                    D2 = 1;
                }

                PostExperience test = new PostExperience(D1,D2,t,0,0,c);        //Create new object with all info we need
                Send(test);
                //Toast toast = Toast.makeText(getActivity().getApplicationContext(), gson.toJson(test), Toast.LENGTH_LONG);
                //toast.show();


            }
        });

        return root;
    }

    public void Send(PostExperience test)
    {
        CreateExperienceTask createTask = new CreateExperienceTask();
        LoginApplication loginApp = new LoginApplication();
        Log.d("GGGGGGGGGGGGGGGGGGGGGGG",loginApp.PREFIX);
        createTask.execute(test);
    }
}

