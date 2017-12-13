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
import net.caesarlegion.drugimpact.Fragments.WelcomeFragment;
import net.caesarlegion.drugimpact.Model.HistoryDatabaseHandler;


public class MainActivity extends AppCompatActivity {

    private BrowseDrugsFragment browseDrugsFragment;
    private RemindersFragment remindersFragment;
    private BrowseExperiencesFragment browseExperiencesFragment;
    private WelcomeFragment welcomeFragment;
    public HistoryDatabaseHandler historyDatabaseHandler;


    public static String URL = "http://192.168.2.11";
    public static String PORT = "9999";
    public static String ADDRESS = URL + ":" + PORT + "/";
    public static Integer CURRENT_USER_ID = 2;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        TabLayout.TabLayoutOnPageChangeListener tabChangeListener = new TabLayout.TabLayoutOnPageChangeListener(tabLayout);
        mViewPager.addOnPageChangeListener(tabChangeListener);

        historyDatabaseHandler = new HistoryDatabaseHandler(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                break;
            case R.id.action_history:
                //TODO: Decrypt data
                break;
            case R.id.action_get_something:



                break;
        }
        return super.onOptionsItemSelected(item);
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
                    if (browseDrugsFragment == null)
                        browseDrugsFragment = new BrowseDrugsFragment();
                    return browseDrugsFragment;
                case 2:
                    if (remindersFragment == null)
                        remindersFragment = new RemindersFragment();
                    return remindersFragment;
                case 3:
                    if (browseExperiencesFragment == null)
                        browseExperiencesFragment = new BrowseExperiencesFragment();
                    return browseExperiencesFragment;
                default:
                    if (welcomeFragment == null)
                        welcomeFragment = new WelcomeFragment();
                    return welcomeFragment;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }
    }
}
