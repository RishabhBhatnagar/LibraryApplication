package org.sfitengg.libraryapplication.navigation_fragments;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.sfitengg.libraryapplication.R;
import org.sfitengg.libraryapplication.main.Model.Book;
import org.sfitengg.libraryapplication.main.Model.BookAdapter;
import org.sfitengg.libraryapplication.main.Model.MainModel;
import org.sfitengg.libraryapplication.main.Presenter.MainActivity;
import org.sfitengg.libraryapplication.main.Presenter.MainPresenter;
import org.sfitengg.libraryapplication.login.Model.LoginModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IssuedBooksFragment extends Fragment{

    List<Book> bookList;
    RecyclerView recyclerView;
    BookAdapter adapter;
    MainModel mainModel;
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Issued Books");
        mainModel = new MainModel();
        return inflater.inflate(R.layout.fragment_issued_books, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //LoginModel x = new LoginModel();


  //  if(x.checkPassword(String pid,String password)) {

        Bundle b = getArguments();
        // List<Book> books = new ArrayList<>();
        List<org.sfitengg.libraryapplication.main.Model.Book> bookList = new ArrayList<>();
        for (Parcelable p : b.getParcelableArrayList("books")) {
            org.sfitengg.libraryapplication.main.Model.Book a = (org.sfitengg.libraryapplication.main.Model.Book) p;
            bookList.add(a);
        }

        //bookList= b.getParcelableArrayList("books");



       // bookList = Arrays.asList((mainModel).getBooks());

        recyclerView = view.findViewById(R.id.recycler_view);
        linearLayout = view.findViewById(R.id.reissue_ll);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new BookAdapter(getActivity(), bookList,mainModel, linearLayout);
        recyclerView.setAdapter(adapter);
    }
}