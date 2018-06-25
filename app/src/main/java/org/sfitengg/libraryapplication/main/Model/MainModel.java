package org.sfitengg.libraryapplication.main.Model;


import org.sfitengg.libraryapplication.main.Presenter.Book;
import org.sfitengg.libraryapplication.main.MainActivity;
import org.sfitengg.libraryapplication.main.Presenter.GoGoGadget;

import java.util.Date;

public class MainModel implements MainModelnterface{


    public int pid;
    private boolean longClicked;
    private int numberOfBooksSelected;
    MainActivity mainActivity= new MainActivity();
    GoGoGadget goGoGadget;



    Book books[] = {
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14)),
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14)),
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14)),
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14)),
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14)),
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14))
    };




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

    // public String a[]= goGoGadget.book();

    @Override


    public Book getBooks() {


        return mainActivity.setter();

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