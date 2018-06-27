package org.sfitengg.libraryapplication.main.Model;

import org.sfitengg.libraryapplication.main.MainActivity;

import java.util.Date;

public class MainModel implements MainModelnterface{

    public int pid;
    private boolean longClicked;
    private int numberOfBooksSelected;

    public MainModel() {
        numberOfBooksSelected = 0;
    }

    public String getAboutLibraryData(){
        // # To-Do # //

        //Get data from library website.
        String data = "This is about library";
        return data;
    }

    @Override
    public String getAboutData() {
        return "about something.";
    }

    @Override
    public boolean isLongClicked() {
        return longClicked;
    }

    @Override
    public void setLongClicked(boolean longClicked) {
        this.longClicked = longClicked;
    }

    @Override
    public int getNumberOfBooksSelected() {
        return numberOfBooksSelected;
    }

    @Override
    public void setNumberOfBooksSelected(int numberOfBooksSelected) {
        this.numberOfBooksSelected = numberOfBooksSelected;
    }
}