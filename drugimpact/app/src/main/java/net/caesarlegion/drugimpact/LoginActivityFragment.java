package net.caesarlegion.drugimpact;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import net.caesarlegion.drugimpact.Fragments.BrowseDrugsFragment;
import net.caesarlegion.drugimpact.ListAdapters.ExperiencesAdapter.ExperienceActivity;
import net.caesarlegion.drugimpact.Model.OnResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {
    final LoginApplication loginApp = new LoginApplication();

    public LoginActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_login, container, false);
        root.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetLoginTask loginTask = new GetLoginTask();
                loginTask.setOnResponseListener(new OnResponseListener<String>() {

                    @Override
                    public void onResponse(String data) {
                        //Toast toast = Toast.makeText(getActivity().getApplicationContext(), data, Toast.LENGTH_SHORT);
                        //toast.show();
                        EditText edit = root.findViewById(R.id.editText);
                        EditText editPass = root.findViewById(R.id.editText2);

                        try {
                            JSONObject info = new JSONObject(data);
                            info = info.getJSONObject("_embedded");
                            JSONArray arr = info.getJSONArray("user");

                            for(int i = 0; i < arr.length();i++)
                            {
                                JSONObject test = new JSONObject(arr.getString(i));

                                if(test.getString("displayName").equals(edit.getText().toString()))
                                {
                                    Intent intent = new Intent(getActivity(),MainActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
                loginTask.execute(loginApp.PREFIX+"/user");

                //Intent intent = new Intent(getActivity(),MainActivity.class);
                //startActivity(intent);
                //Toast toast = Toast.makeText(getActivity().getApplicationContext(), "You are logging in", Toast.LENGTH_SHORT);
                //toast.show();
            }
        });
        return root;
    }
}
