package com.werunthisleague.baseballleaguemanager.activity;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.werunthisleague.baseballleaguemanager.R;
import com.werunthisleague.baseballleaguemanager.fragment.GameTrackerFragment;
import com.werunthisleague.baseballleaguemanager.fragment.NavigationDrawerFragment;
import com.werunthisleague.baseballleaguemanager.fragment.PlayerHomeScreenFragement;


public class HomeScreen extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, PlayerHomeScreenFragement.OnFragmentInteractionListener,
        GameTrackerFragment.OnFragmentInteractionListener{

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment frags [] = new Fragment[] {PlayerHomeScreenFragement.newInstance("", ""), GameTrackerFragment.newInstance("",
                "")};
        fragmentManager.beginTransaction()
                .replace(R.id.container, frags[position])
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.home_screen_section);
                break;
            case 2:
                mTitle = getString(R.string.game_tracker_section);
                break;
            case 3:
                mTitle = getString(R.string.my_team_section);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home_screen, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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

    public void onFragmentInteraction(Uri uri) {
    }

    public void onFragmentInteraction(String uri) {
    }
}
