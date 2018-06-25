package org.sfitengg.libraryapplication.navigation_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.sfitengg.libraryapplication.R;
//import org.sfitengg.libraryapplication.main.Model.Book;
import org.sfitengg.libraryapplication.main.Model.BookAdapter;
import org.sfitengg.libraryapplication.main.Model.MainModel;
import org.sfitengg.libraryapplication.main.Presenter.Book;

import java.util.Arrays;
import java.util.List;


public class IssuedBooksFragment extends Fragment{
    TextView textView;
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

        bookList = Arrays.asList((mainModel).getBooks());
        if(bookList.isEmpty()){
            Log.d("bb","empty");
        }
        else{

            Log.d("bb","fuller");
            String  a = bookList.toString();
            textView = view.findViewById(R.id.textView);
            textView.setText(a);

        }




            recyclerView = view.findViewById(R.id.recycler_view);
            linearLayout = view.findViewById(R.id.reissue_ll);

            recyclerView.setHasFixedSize(true);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


            adapter = new BookAdapter(getActivity(), bookList, mainModel, linearLayout);
            recyclerView.setAdapter(adapter);

    }
}