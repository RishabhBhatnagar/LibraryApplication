package org.sfitengg.libraryapplication.main;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.sfitengg.libraryapplication.main.Model.Book;
import org.sfitengg.libraryapplication.main.Presenter.MainPresenter;
import org.sfitengg.libraryapplication.main.View.MainViewInterface;
import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.login.LoginActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewInterface, MyCallback {

    private DrawerLayout drawerLayout;
    private AppBarLayout appBarLayout;
    public Toolbar toolbar;
    private NavigationView navigationView;
    private TextView statusTextView;
    private Button button;

    private ActionBarDrawerToggle toggle;
    public static final int REQUEST_CODE_GET_PID = 2048;  /*any random number.*/
    public static final String pid_key = "pid";
    private SharedPreferences preferences;








    //vd
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

    static String pid = "153044";
    static String pwd = "153044";

    Handler handler = new Handler();
    //vd






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
            rest_code();
        }

    }

    void rest_code() {

        //GUI component's initialization.
        drawerLayout = findViewById(R.id.drawer_layout);
        appBarLayout = findViewById(R.id.app_bar_layout);
        navigationView = findViewById(R.id.navigation_view);
        statusTextView = findViewById(R.id.status);
        button = findViewById(R.id.button);

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











        //vd

        boolean testing = false;

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // For now, keeping here so that we can get outdocs after login
                GoGoGadget gForBooks = new GoGoGadget((MyCallback) MainActivity.this,
                        bundleURLs,
                        GoGoGadget.GET_OUT_DOCS,
                        handler);
                gForBooks.setCookies(goGoGadget.getCookies());

                new Thread(gForBooks).start();
            }
        });
        //vd











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




    //vd

    @Override
    public void sendBooksToCaller(List<Book> books) {
        st(books.toString());
    }

    @Override
    public void sendStudentNameToCaller(String name) {
        st(name);
    }

    @Override
    public void passErrorsToCaller(int errorCode) {
        // TODO: Error handling here
        // Check the errorCode against the GoGoGadget constants
        st("ERRORCODE: " + errorCode);
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
    }

    @Override
    public void userHasBorrowedNoBooks() {
        st("User has borrowed no books");
    }
    public void st(String string){
        statusTextView.setText(string);
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
    //vd














}