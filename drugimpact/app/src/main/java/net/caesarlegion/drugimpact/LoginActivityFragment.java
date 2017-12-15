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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {

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

                        EditText editEmail = root.findViewById(R.id.editText);
                        EditText editPass = root.findViewById(R.id.editText2);

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
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Bad Password", Toast.LENGTH_SHORT);
                                        toast.show();
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
                loginTask.execute(MainActivity.ADDRESS+"user");
            }
        });
        return root;
    }


/*
    //This Hash Function was taken from
    //https://stackoverflow.com/questions/4846484/md5-hashing-in-android
    public static final String md5(final String s)
    {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
*/
}
