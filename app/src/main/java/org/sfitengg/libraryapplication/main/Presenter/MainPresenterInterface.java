package org.sfitengg.libraryapplication.main.Presenter;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

public interface MainPresenterInterface {
    int whichYear(int pid);
    void handleNavigationView(NavigationView navigationView, DrawerLayout drawerLayout);
    void reissueBooks();
}
