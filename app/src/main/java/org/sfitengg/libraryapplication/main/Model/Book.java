package org.sfitengg.libraryapplication.main.Model;

import java.util.Date;

public class Book{

    public boolean isSelected(){
        return selected;
    }
    public void setSelected(boolean state){
        this.selected = state;
    }
    public int getReissueCount() {
        return reissueCount;
    }

    public int getFine() {
        return fine;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getBookName() {
        return bookName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    int reissueCount;
    int fine;
    String authorName;
    String bookName;
    Date issueDate;
    Date returnDate;
    boolean selected;

    public Book(int reissueCount, int fine, String authorName, String bookName, Date issue_date, Date return_date) {
        this.reissueCount = reissueCount;
        this.fine = fine;
        this.authorName = authorName;
        this.bookName = bookName;
        this.issueDate = issue_date;
        this.returnDate = return_date;
        this.selected = false;
    }
}
