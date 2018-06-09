package org.sfitengg.libraryapplication.main.Model;

import java.util.Date;

class Book{
    int reissueCount;
    int fine;
    String authorName;
    String bookName;
    Date issueDate;
    Date returnDate;

    public Book(int reissueCount, int fine, String authorName, String bookName, Date issue_date, Date return_date) {
        this.reissueCount = reissueCount;
        this.fine = fine;
        this.authorName = authorName;
        this.bookName = bookName;
        this.issueDate = issue_date;
        this.returnDate = return_date;
    }
}
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

    public Book[] getBooks(){
        return books;
    }

}