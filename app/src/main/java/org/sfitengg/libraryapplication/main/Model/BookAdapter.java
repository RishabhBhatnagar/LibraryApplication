package org.sfitengg.libraryapplication.main.Model;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.main.Presenter.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{

    private Context context;
    private List<Book> bookList;
    private MainModel mainModel;
    private LinearLayout reissue_ll;
    private Button reissueButton;
    private List<Book> selectedBooks;

    private boolean declared;

    public BookAdapter(final Context context, List<Book> bookList, MainModel mainModel, LinearLayout reissueLL) {
        this.context = context;
        this.bookList = bookList;
        this.mainModel = mainModel;
        this.reissue_ll = reissueLL;
        selectedBooks = new ArrayList<Book>();

        reissueButton = ((Activity) context).findViewById(R.id.reissue_button);
        reissueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Reissue Button Clicked.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(
                LayoutInflater.from(context).                               //inflater
                        inflate(R.layout.individual_book_layout, null) //view
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder holder, int position) {
        final Book book = bookList.get(position);
        holder.name.setText(book.getBookName());
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selectedBooks.add(book);
                reissue_ll.setVisibility(View.VISIBLE);
                mainModel.setLongClicked(true);
                mainModel.setNumberOfBooksSelected(mainModel.getNumberOfBooksSelected()+1);
                book.setSelected(true);
                holder.linearLayout.setBackgroundColor(Color.BLUE);
                return true;
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainModel.isLongClicked()){
                    if(book.isSelected()){
                        mainModel.setNumberOfBooksSelected(mainModel.getNumberOfBooksSelected()-1);
                        book.setSelected(false);
                        holder.linearLayout.setBackgroundColor(Color.WHITE);

                        if(mainModel.getNumberOfBooksSelected()==0){
                            reissue_ll.setVisibility(View.GONE);
                        }
                    }
                    else{
                        selectedBooks.add(book);
                        if(mainModel.getNumberOfBooksSelected()>0){
                            reissueButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int a = 78;
                                }
                            });
                            reissue_ll.setVisibility(View.VISIBLE);
                        }

                        mainModel.setNumberOfBooksSelected(mainModel.getNumberOfBooksSelected()+1);
                        book.setSelected(true);
                        holder.linearLayout.setBackgroundColor(Color.BLUE);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        LinearLayout linearLayout;

        public BookViewHolder(final View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            name = itemView.findViewById(R.id.name);
        }
    }
}