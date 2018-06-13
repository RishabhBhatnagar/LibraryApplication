package org.sfitengg.libraryapplication.main.Model;

import java.util.Date;

public class MainModel{

    public int pid;
    Book books[] = {
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14)),
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14)),
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14)),
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14)),
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14)),
            new Book(1, 0, "Kumbhojkar", "AM", new Date(2018, 6, 12), new Date(2018, 6,14))
    };

    public String getAboutLibraryData(){
        // # To-Do # //

        //Get data from library website.
        String data = "This is about library";
        return data;
    }

    public Book[] getBooks(){
        return books;
    }

}