package org.sfitengg.libraryapplication.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.main.Model.Book;
import org.sfitengg.libraryapplication.main.Model.BookAdapter;
import org.sfitengg.libraryapplication.main.Model.MainModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 */

public class IssuedBooksFragment extends Fragment{

    List<Book> bookList;
    RecyclerView recyclerView;
    BookAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_issued_books, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookList = Arrays.asList((new MainModel()).getBooks());
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new BookAdapter(getActivity(), bookList);
        recyclerView.setAdapter(adapter);
    }
}
