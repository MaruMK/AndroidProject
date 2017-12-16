package net.caesarlegion.drugimpact.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import net.caesarlegion.drugimpact.R;

/**
 * Created by Maru on 2017-12-16.
 */

public class DrugInfoActivity extends AppCompatActivity{
    TextView drugName;
    TextView drugUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.fragment_drug_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View rootView =
        drugName =
        Intent intent = getIntent();
        String drugName = intent.getStringExtra("drug_name");
        String drugUrl = intent.getStringExtra("drug_url");
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}