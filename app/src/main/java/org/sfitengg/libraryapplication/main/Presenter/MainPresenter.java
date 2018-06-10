package org.sfitengg.libraryapplication.main.Presenter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.sfitengg.libraryapplication.AboutLibraryFragment;
import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.login.Model.LoginModel;
import org.sfitengg.libraryapplication.main.MainActivity;
import org.sfitengg.libraryapplication.main.Model.MainModel;
import org.sfitengg.libraryapplication.main.View.MainViewInterface;

import java.util.Calendar;

public class MainPresenter extends MainModel implements MainPresenterInterface {
    private MainViewInterface mainView;

    public MainPresenter(MainViewInterface mainView){
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
    public void handleSearchView(MaterialSearchView searchView) {
        searchView.closeSearch();
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    @Override
    public void handleNavigationView(NavigationView navigationView, final DrawerLayout drawerLayout) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = null;
                        switch(item.getItemId()){
                            case R.id.about_library :
                                fragment = new AboutLibraryFragment();
                                break;
                        }

                        if(fragment != null){
                            mainView.setFragment(fragment);
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    }
                }
        );
    }
}