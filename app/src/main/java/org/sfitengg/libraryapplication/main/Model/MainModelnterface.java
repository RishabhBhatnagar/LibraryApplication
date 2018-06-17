package org.sfitengg.libraryapplication.main.Model;

import org.sfitengg.libraryapplication.main.Presenter.MainActivity;

public interface MainModelnterface {
    String getAboutLibraryData();
    String getAboutData();
    Book[] getBooks();
    boolean isLongClicked();
    void setLongClicked(boolean longClicked);
    int getNumberOfBooksSelected();
    void setNumberOfBooksSelected(int numberOfBooksSelected);
}
