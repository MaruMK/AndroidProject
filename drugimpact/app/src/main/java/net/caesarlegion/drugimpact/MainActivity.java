package net.caesarlegion.drugimpact;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;

import net.caesarlegion.drugimpact.Control.GETObject;
import net.caesarlegion.drugimpact.Control.OnDownloadedListener;
import net.caesarlegion.drugimpact.Fragments.BrowseDrugsFragment;
import net.caesarlegion.drugimpact.Fragments.BrowseExperiencesFragment;
import net.caesarlegion.drugimpact.Fragments.RemindersFragment;
import net.caesarlegion.drugimpact.Fragments.SettingsFragment;
import net.caesarlegion.drugimpact.Fragments.WelcomeFragment;
import net.caesarlegion.drugimpact.Model.DrugSafety;
import net.caesarlegion.drugimpact.Model.DrugSafetyData;
import net.caesarlegion.drugimpact.Model.HistoryDatabaseHandler;

/**
 * Author: Gabriel Charlebois
 * Purpose: Initialize the main UI components and globals of the app
 */
public class MainActivity extends AppCompatActivity {

    //Declare parameter identifiers used to communication between activities
    public static class params {
        public static String USER_ID = "user";
        public static String KEY = "key";
    }
    //Declare some global variables
    public static String URL = "http://192.168.2.11"; //"http://10.0.2.2"
    public static String PORT = "9999";
    public static String ADDRESS = URL + ":" + PORT + "/";

    //Used to permanently store user's emergency info
    SharedPreferences emergencyPrefs;

    public static boolean foreground = true;


    //Declare some UI elements
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;


    //Hold the userId and key for database identification and encryption
    private String userId;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emergencyPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Initialize our id and key for the database
        Intent fromLogin = getIntent();
        userId = fromLogin.getStringExtra(params.USER_ID);
        key = fromLogin.getStringExtra(params.KEY);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());



        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //Get out tab layout object and fill it with the appropriate tab icons
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_tab_welcome));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_tab_drugs));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_tab_history));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_tab_exp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_tab_settings));

        //Add the dynamic components to our tabbed layout
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    @Override
    public void onPause(){
        super.onPause();
        SharedPreferences.Editor edit = emergencyPrefs.edit();
        edit.putString("EmergencyNumber", DrugSafetyData.EMERGENCY_NUMBER);
        edit.putString("EmergencyMessage", DrugSafetyData.EMERGENCY_MESSAGE);
        edit.commit();
        foreground = false;
    }

    @Override
    public void onResume(){
        super.onResume();
        DrugSafetyData.EMERGENCY_NUMBER = emergencyPrefs.getString("EmergencyNumber", "555-555-5555");
        DrugSafetyData.EMERGENCY_MESSAGE = emergencyPrefs.getString("EmergencyMessage", "Emergency Message");
        foreground = true;
    }


    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        DrugSafetyData.EMERGENCY_NUMBER = savedInstanceState.getString("EmergencyNumber");
        DrugSafetyData.EMERGENCY_MESSAGE = savedInstanceState.getString("EmergencyMessage");
    }


    /**
     * Purpose: Map a tab id to a specific fragment
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Purpose: Given a position in the tab layout, return a fragment.
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 1:
                    return new BrowseDrugsFragment();
                case 2:
                    //For the reminder, we want to bundle the necessary user data for the database.
                    Bundle bundle = new Bundle();
                    bundle.putString(params.USER_ID, userId);
                    bundle.putString(params.KEY, key);

                    RemindersFragment remindersFragment = new RemindersFragment();
                    remindersFragment.setArguments(bundle);
                    return remindersFragment;
                case 3:
                    return new BrowseExperiencesFragment();
                case 4:
                    return new SettingsFragment();
                default:
                    return new WelcomeFragment();
            }
        }

        /**
         * Is called to instantiate the fragment for the given page.
         * @return
         */
        @Override
        public int getCount() {
            // Show 4 total pages.
            return 5;
        }
    }
}
