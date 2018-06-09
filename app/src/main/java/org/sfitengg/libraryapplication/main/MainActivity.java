package org.sfitengg.libraryapplication.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.sfitengg.libraryapplication.main.Presenter.MainPresenter;
import org.sfitengg.libraryapplication.main.View.MainViewInterface;
import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    public static final int REQUEST_CODE_GET_PID = 2048;  /*any random number.*/
    int pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Directly redirecting to login
         * without finishing the main activity so that,
         * after successful login, user can come back to main activity again.
         */
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(loginIntent, REQUEST_CODE_GET_PID);
    }

    void rest_code(){

        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_drawer_open, R.string.app_drawer_close);
        toggle.syncState();
        //drawerLayout.setDrawerListener();

        MainPresenter presenter = new MainPresenter(this);
        int currentYear = presenter.whichYear(pid);
        switch(currentYear){
            case 1 : setBackground(ContextCompat.getColor(this, R.color.fe));break;
            case 2 : setBackground(ContextCompat.getColor(this, R.color.se));break;
            case 3 : setBackground(ContextCompat.getColor(this, R.color.te));break;
            case 4 : setBackground(ContextCompat.getColor(this, R.color.be));break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GET_PID && resultCode == Activity.RESULT_OK && data != null){
            this.pid = data.getIntExtra("pid", 162009);
            rest_code();
        }
    }

    @Override
    public void update_text(String message) {
        int a = 7;
    }

    public void setBackground(int id){
        drawerLayout.setBackgroundColor(id);
    }
}