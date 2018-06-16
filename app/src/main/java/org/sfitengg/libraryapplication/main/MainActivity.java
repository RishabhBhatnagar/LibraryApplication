package org.sfitengg.libraryapplication.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.sfitengg.libraryapplication.main.Presenter.MainPresenter;
import org.sfitengg.libraryapplication.main.View.MainViewInterface;
import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    private DrawerLayout drawerLayout;
    private AppBarLayout appBarLayout;
    public Toolbar toolbar;
    private MaterialSearchView searchView;
    private NavigationView navigationView;

    private ActionBarDrawerToggle toggle;
    public static final int REQUEST_CODE_GET_PID = 2048;  /*any random number.*/
    private int pid;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getApplicationContext().getSharedPreferences("login_preferences", MODE_PRIVATE);

        /* Directly redirecting to login
         * without finishing the main activity so that,
         * after successful login, user can come back to main activity again.
         */

        boolean isUserLoggedIn = preferences.getBoolean("loggedIn", false);
        if(!isUserLoggedIn){
            //start login activity.
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            preferences.edit().putBoolean("loggedIn", true).apply();
            startActivityForResult(loginIntent, REQUEST_CODE_GET_PID);
        }
        else{
            Toast.makeText(this, "inside else", Toast.LENGTH_SHORT).show();
            rest_code();
        }

    }

    void rest_code() {

        //GUI component's initialization.
        drawerLayout = findViewById(R.id.drawer_layout);
        appBarLayout = findViewById(R.id.app_bar_layout);
        navigationView = findViewById(R.id.navigation_view);

        toolbar = findViewById(R.id.my_toolbar);
        searchView = findViewById(R.id.search_view);

        /**Navigation bar setup**/
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_drawer_open, R.string.app_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        /*Navigation bar setup*/

        MainPresenter presenter = new MainPresenter(this);

        /**
         * Setup background wallpaper depending upon
         * the current year in which user is.
         **/
        int currentYear = this.pid;
        try {
            currentYear = presenter.whichYear(pid);
        }
        catch (Exception e){
            pid = preferences.getInt("pid", 152009);
            currentYear = presenter.whichYear(pid);
        }
        switch (currentYear) {
            case 1:
                setBackground(ContextCompat.getColor(this, R.color.fe));
                break;
            case 2:
                setBackground(ContextCompat.getColor(this, R.color.se));
                break;
            case 3:
                setBackground(ContextCompat.getColor(this, R.color.te));
                break;
            case 4:
                setBackground(ContextCompat.getColor(this, R.color.be));
                break;
        }
        /*setting up wallpaper end*/

        presenter.handleNavigationView(navigationView, drawerLayout);
        preferences.edit().putBoolean("loggedIn", true);
        preferences.edit().apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GET_PID && resultCode == Activity.RESULT_OK && data != null){
            pid = data.getIntExtra("pid", 162009);
            preferences.edit().putInt("pid", this.pid);
            preferences.edit().apply();
            rest_code();
        }
    }

    public void setBackground(int id){
        appBarLayout.setBackgroundColor(id);
    }

    @Override
    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.app_bar_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
    }
}