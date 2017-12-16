package net.caesarlegion.drugimpact;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.caesarlegion.drugimpact.Model.OnResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {
 private Context c = this.getContext();
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

                        EditText editEmail = root.findViewById(R.id.editEmail);
                        EditText editPass = root.findViewById(R.id.editPassword);

                        try {
                            JSONObject info = new JSONObject(data);
                            info = info.getJSONObject("_embedded");
                            JSONArray arr = info.getJSONArray("user");

                            for(int i = 0; i < arr.length();i++)
                            {
                                JSONObject item = new JSONObject(arr.getString(i));

                                if(item.getString("email").equals(editEmail.getText().toString()))
                                {
                                    if(item.getString("pass").equals(editPass.getText().toString()))
                                    {
                                        Intent intent = new Intent(getActivity(),MainActivity.class);
                                        //Give the user id and the password to activity so it can initialize the database accordingly
                                        intent.putExtra(MainActivity.params.USER_ID, Integer.toString(i + 1));
                                        intent.putExtra(MainActivity.params.KEY, item.getString("encryptionKey") );
                                        startActivity(intent);
                                    }
                                }
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
                LoginApplication loginApp = new LoginApplication();
                loginTask.execute(MainActivity.ADDRESS+"user");
            }
        });

        View button = root.findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentlol = new Intent(getActivity(),CreateUserActivity.class);
                startActivity(intentlol);
            }
        });
        return root;
    }

}
