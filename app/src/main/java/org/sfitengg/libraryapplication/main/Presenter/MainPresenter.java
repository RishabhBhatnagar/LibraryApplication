package org.sfitengg.libraryapplication.main.Presenter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;

import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.login.Model.LoginModel;
import org.sfitengg.libraryapplication.main.Model.MainModel;
import org.sfitengg.libraryapplication.main.View.MainViewInterface;

import java.util.Calendar;

public class MainPresenter implements MainPresenterInterface {
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
}