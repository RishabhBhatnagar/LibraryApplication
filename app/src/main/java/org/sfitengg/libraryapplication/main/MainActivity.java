package org.sfitengg.libraryapplication.main;

import android.annotation.SuppressLint;
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

import org.sfitengg.libraryapplication.main.Presenter.Book;
import org.sfitengg.libraryapplication.main.Presenter.GoGoGadget;
import org.sfitengg.libraryapplication.main.Presenter.MainPresenter;
import org.sfitengg.libraryapplication.main.Presenter.MyCallback;
import org.sfitengg.libraryapplication.main.View.MainViewInterface;
import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.login.LoginActivity;
import org.sfitengg.libraryapplication.navigation_fragments.HomeFragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.navigation_fragments.IssuedBooksFragment;

import java.util.ArrayList;
import java.util.List;

import static android.speech.SpeechRecognizer.ERROR_SERVER;
import static org.sfitengg.libraryapplication.main.Presenter.GoGoGadget.ERROR_INCORRECT_PID_OR_PASSWORD;
import static org.sfitengg.libraryapplication.main.Presenter.GoGoGadget.ERROR_NOT_LOGGED_IN;
import static org.sfitengg.libraryapplication.main.Presenter.GoGoGadget.ERROR_NO_INTERNET;
import static org.sfitengg.libraryapplication.main.Presenter.GoGoGadget.ERROR_SERVER_UNREACHABLE;
import static org.sfitengg.libraryapplication.main.Presenter.GoGoGadget.RETURN_LIST_BOOKS;

public class MainActivity extends AppCompatActivity implements MainViewInterface,MyCallback {

    private DrawerLayout drawerLayout;
    private AppBarLayout appBarLayout;
    public Toolbar toolbar;
    private NavigationView navigationView;

    private ActionBarDrawerToggle toggle;
    public static final int REQUEST_CODE_GET_PID = 2048;  /*any random number.*/
    public static final String pid_key = "pid";
    private SharedPreferences preferences;

    // Main Page where login Form is present
    static String urlMainPage = "http://115.248.171.105:82/webopac/";

    // Complete url to the form action attribute
    // where we send a POST
    static String urlLoginFormAction = urlMainPage + "opac.asp?m_firsttime=Y&m_memchk_flg=T";

    // Url of docs page
    static String urlOutDocsPage = "http://115.248.171.105:82/webopac/l_renew.asp";

    // Url where reissue form is sent
    // static String urlOutDocsFormAction = l_renew1.asp;

    Bundle bundleURLs;

    public static String pid = "171001";
    public static String pwd = "171001";

    TextView tv;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        boolean testing = true;

        if (testing) {
            urlMainPage = " http://192.168.1.66:5000/";
            urlLoginFormAction = urlMainPage + "userpage";
            urlOutDocsPage = urlMainPage + "out_docs";
            pid = "4";
            pwd = "4";
        }

        // Create a bundle to pass in the URLs to the GoGoGadget object
        bundleURLs = new Bundle();
        bundleURLs.putString(GoGoGadget.keyMainPage, urlMainPage);
        bundleURLs.putString(GoGoGadget.keyLoginForm, urlLoginFormAction);
        bundleURLs.putString(GoGoGadget.keyOutDocs, urlOutDocsPage);


        // First login
        final GoGoGadget goGoGadget = new GoGoGadget((MyCallback) this,
                bundleURLs,
                GoGoGadget.LOGIN_AND_GET_COOKIES,
                handler);
        new Thread(goGoGadget).start();


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
            rest_code();
        }

    }

    void rest_code() {

        //GUI component's initialization.
        drawerLayout = findViewById(R.id.drawer_layout);
        appBarLayout = findViewById(R.id.app_bar_layout);
        navigationView = findViewById(R.id.navigation_view);

        toolbar = findViewById(R.id.my_toolbar);

        /**Navigation bar setup**/
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_drawer_open, R.string.app_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        /*Navigation bar setup*/

        preferences.edit().putBoolean("loggedIn", true);
        preferences.edit().apply();

        MainPresenter presenter = new MainPresenter(this, preferences);
        presenter.setWallpaper();
        presenter.handleNavigationView(navigationView, drawerLayout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_GET_PID && resultCode == Activity.RESULT_OK && data != null){
            int pid = data.getIntExtra(pid_key, 112009);
            (preferences.edit()).putInt(pid_key, pid).apply();
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
    List<Book>BOOK;
    @Override
    public void sendBooksToCaller(List<Book> books) {
        if ( books != null ) {

            BOOK=  books;
        }
    }

    public Book setter(){

        return (Book) BOOK;
    }

    @Override
    public void sendStudentNameToCaller(String name) {
        tv.setText(name);
    }


    @Override
    public void passErrorsToCaller(int errorCode) {
        switch(errorCode)
        {
            case ERROR_NOT_LOGGED_IN:
                Toast.makeText(getApplicationContext(), "ERROR_NOT_LOGGED_IN", Toast.LENGTH_SHORT).show();
            case ERROR_NO_INTERNET:
                Toast.makeText(getApplicationContext(), "ERROR_NO_INTERNET", Toast.LENGTH_SHORT).show();
            case ERROR_SERVER_UNREACHABLE:
                Toast.makeText(getApplicationContext(), "ERROR_SERVER_UNREACHABLE", Toast.LENGTH_SHORT).show();
            case ERROR_INCORRECT_PID_OR_PASSWORD:
                Toast.makeText(getApplicationContext(), "ERROR_INCORRECT_PID_OR_PASSWORD", Toast.LENGTH_SHORT).show();


        }
//        tv.setText("ERRORCODE: " + errorCode);
    }

    @Override
    public String getPid() {
        return pid;
    }

    @Override
    public String getPwd() {
        return pwd;
    }

    @Override
    public boolean isConnectedToInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
       // return false;
    }

    @Override
    public void userHasBorrowedNoBooks() {
        //tv.setText("User has borrowed no books");
    }
}