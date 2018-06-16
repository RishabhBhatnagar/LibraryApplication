package org.sfitengg.libraryapplication.main.Model;

public interface MainModelnterface {
    String getAboutLibraryData();
    Book[] getBooks();
    boolean isLongClicked();
    void setLongClicked(boolean longClicked);
    int getNumberOfBooksSelected();
    void setNumberOfBooksSelected(int numberOfBooksSelected);
}
