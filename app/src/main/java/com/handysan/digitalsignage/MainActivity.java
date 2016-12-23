package com.handysan.digitalsignage;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clientcomm.server.TCPServer;
import com.handysan.digitalsignage.fragments.About;
import com.handysan.digitalsignage.fragments.AndroidID;
import com.handysan.digitalsignage.fragments.DigitalSignage;
import com.handysan.digitalsignage.fragments.Registration;
import com.handysan.digitalsignage.fragments.Settings;
import com.handysan.digitalsignage.media.CampaignUpdateHandler;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AndroidID.OnFragmentInteractionListener, DigitalSignage.OnFragmentInteractionListener,  Registration.OnFragmentInteractionListener, Settings.OnFragmentInteractionListener, About.OnFragmentInteractionListener {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    Fragment signageFragment = new DigitalSignage();
    Fragment registrationFragment = new Registration();
    Fragment deviceId = new AndroidID();
    Fragment SettingsFragment = new Settings();
    Fragment aboutFrame = new About();

    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("HandySan", "Main Activity Started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.updateFragmentFrame(signageFragment);
        /* new ServerInitialisationTask().execute(this.getApplicationContext()); */


        /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */


        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void updateFragmentFrame(Fragment aFragment) {
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.fragment_placeholder, aFragment);
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_signage) {
            // Handle the camera action
            Log.i("HandySan", "Signage Fragment active");
            this.updateFragmentFrame(this.signageFragment);
            Toast.makeText( this, "Signage", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_registration) {
            Log.i("HandySan", "Registration Fragment active");
            this.updateFragmentFrame(this.registrationFragment);
            Toast.makeText( this, "Registration", Toast.LENGTH_SHORT).show();}
        else if (id == R.id.nav_device_id) {
            Log.i("HandySan", "Device ID Fragment active");
                this.updateFragmentFrame(this.deviceId);
                Toast.makeText( this, "Registration", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Log.i("HandySan", "Signage Fragment active");
            this.updateFragmentFrame(this.SettingsFragment);
            Toast.makeText( this, "Settings", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about) {
            Log.i("HandySan", "About Fragment active");
            this.updateFragmentFrame(this.aboutFrame);
            Toast.makeText( this, "About", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
