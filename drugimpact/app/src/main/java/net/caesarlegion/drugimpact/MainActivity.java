package net.caesarlegion.drugimpact;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
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
import net.caesarlegion.drugimpact.Model.HistoryDatabaseHandler;


public class MainActivity extends AppCompatActivity {


    public HistoryDatabaseHandler historyDatabaseHandler;


    public static String URL = "http://192.168.2.11";
    public static String PORT = "9999";
    public static String ADDRESS = URL + ":" + PORT + "/";
    public static Integer CURRENT_USER_ID = 2;

    private SectionsPagerAdapter mSectionsPagerAdapter;




    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_tab_welcome));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_tab_drugs));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_tab_history));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_tab_exp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_action_tab_settings));

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        historyDatabaseHandler = new HistoryDatabaseHandler(this);
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position){
                case 1:
                    return new BrowseDrugsFragment();
                case 2:
                    return new RemindersFragment();
                case 3:
                    return new BrowseExperiencesFragment();
                case 4:
                    return new SettingsFragment();
                default:
                    return new WelcomeFragment();
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 5;
        }
    }
}
