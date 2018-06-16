package org.sfitengg.libraryapplication.main.Presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.Toast;

import org.sfitengg.libraryapplication.main.MainActivity;
import org.sfitengg.libraryapplication.navigation_fragments.AboutLibraryFragment;
import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.navigation_fragments.IssuedBooksFragment;
import org.sfitengg.libraryapplication.main.Model.MainModel;
import org.sfitengg.libraryapplication.main.View.MainViewInterface;

import java.util.Calendar;

public class MainPresenter extends MainModel implements MainPresenterInterface {

    private MainViewInterface mainView;
    private SharedPreferences preferences;

    public MainPresenter(MainViewInterface mainView, SharedPreferences preferences){
        this.preferences = preferences;
        this.mainView = mainView;
    }

    @Override
    public int whichYear(int pid) {
        String pidString = String.valueOf(pid);
        int year = Calendar.getInstance().get(Calendar.YEAR)%100;   // extracting 18 out of 2018.
        int currentYear = year-Integer.parseInt(pidString.substring(0, 2));
        return currentYear;
    }

    @Override
    public void setWallpaper() {

        /**
         * Setup background wallpaper depending upon
         * the current year in which user is.
         **/
        int currentYear;
        try {
            int pid = preferences.getInt("pid", 112009);
            currentYear = whichYear(pid);
            Toast.makeText((Activity) mainView, String.valueOf(currentYear), Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText((Activity) mainView, "in catch", Toast.LENGTH_SHORT).show();
            pid = preferences.getInt("pid", 112009);  //default is a random value
            currentYear = whichYear(pid);
        }
        switch (currentYear) {
            case 1:
                mainView.setBackground(ContextCompat.getColor((Activity)mainView, R.color.fe));
                break;
            case 2:
                mainView.setBackground(ContextCompat.getColor((Activity)mainView, R.color.se));
                break;
            case 3:
                mainView.setBackground(ContextCompat.getColor((Activity)mainView, R.color.te));
                break;
            case 4:
                mainView.setBackground(ContextCompat.getColor((Activity)mainView, R.color.be));
                break;
            default:
                mainView.setBackground(ContextCompat.getColor((Activity)mainView, R.color.guest));
                break;
        }
        /*setting up wallpaper end*/
    }

    @Override
    public void handleNavigationView(NavigationView navigationView, final DrawerLayout drawerLayout) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;
                Bundle bundle = new Bundle();

                switch(item.getItemId()){
                    case R.id.about_library :
                        bundle.putString("data", MainPresenter.super.getAboutLibraryData());
                        fragment = new AboutLibraryFragment();
                        fragment.setArguments(bundle);
                        break;

                        case R.id.issued_books:
                            fragment = new IssuedBooksFragment();
                            break;
                    case R.id.sign_out:
                        //show alert dialog:
                        AlertDialog.Builder builder = new AlertDialog.Builder((Activity)mainView);
                        builder.setTitle("Sign Out");
                        builder.setMessage("Are you sure you want to sign out??");
                        builder.setCancelable(false);

                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                preferences.edit().clear().apply();
                                ((Activity) mainView).recreate();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                }

                if(fragment != null) mainView.setFragment(fragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public void reissueBooks() {
    }
}