package org.sfitengg.libraryapplication.main.Presenter;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;

/**
 * Created by vinay on 14-06-2018.
 */

public class Book implements Parcelable {

    // Implement Parcelable
    // so that we can pass Book into a Bundle

    // All variables are kept as string
    // They can be parsed later in the calling code

    int reissueCount;
    int fine;
    String authorName;
    String bookName;
    Date issueDate;
    Date returnDate;
    boolean selected;
    private String acc_no;
    private String title;
    private String dueDate;
    private String fineAmount;
    private String renewCount;
    private String reservations;
    private boolean canRenew;


    public boolean isSelected(){
        return selected;
    }
    public void setSelected(boolean state){
        this.selected = state;
    }
//    public int getReissueCount() {
//        return reissueCount;
//    }

//    public int getFine() {
//        return fine;
//    }
//
//    public String getAuthorName() {
//        return authorName;
//    }

    public String getBookName() {
        return bookName;
    }

//    public Date getIssueDate() {
//        return issueDate;
//    }

//    public Date getReturnDate() {
//        return returnDate;
//    }
    public Book(int reissueCount, int fine, String authorName, String bookName, Date issue_date, Date return_date) {
        this.reissueCount = reissueCount;
        this.fine = fine;
        this.authorName = authorName;
        this.bookName = bookName;
        this.issueDate = issue_date;
        this.returnDate = return_date;
        this.selected = false;
    }





    // Parcellable overrides
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(acc_no);
        dest.writeString(title);
        dest.writeString(dueDate);
        dest.writeString(fineAmount);
        dest.writeString(renewCount);
        dest.writeString(reservations);

        // Refer:
        // https://stackoverflow.com/questions/6201311/how-to-read-write-a-boolean-when-implementing-the-parcelable-interface
        dest.writeByte((byte) (canRenew ? 1 : 0));
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    private Book(Parcel in) {
        acc_no = in.readString();
        title = in.readString();
        dueDate = in.readString();
        fineAmount = in.readString();
        renewCount = in.readString();
        reservations = in.readString();

        // Refer:
        // https://stackoverflow.com/questions/6201311/how-to-read-write-a-boolean-when-implementing-the-parcelable-interface
        canRenew = in.readByte() != 0;

    }

    public Book() {
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public void setFineAmount(String fineAmount) {
        this.fineAmount = fineAmount;
    }

    public void setRenewCount(String renewCount) {
        this.renewCount = renewCount;
    }

    public void setReservations(String reservations) {
        this.reservations = reservations;
    }

    public void setCanRenew(boolean canRenew) {
        this.canRenew = canRenew;
    }

    @Override
    public String toString() {
        return "Book{" +
                "acc_no='" + acc_no + '\'' +
                ", title='" + title + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", fineAmount='" + fineAmount + '\'' +
                ", renewCount='" + renewCount + '\'' +
                ", reservations='" + reservations + '\'' +
                ", canRenew=" + canRenew +
                '}';
    }
}
