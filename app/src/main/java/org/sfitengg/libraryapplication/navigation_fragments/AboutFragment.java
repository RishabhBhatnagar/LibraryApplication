package org.sfitengg.libraryapplication.navigation_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.sfitengg.libraryapplication.R;

/**
 *
 */

public class AboutFragment extends Fragment{

    public TextView description;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("About");
        return inflater.inflate(R.layout.fragment_about, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        description = view.findViewById(R.id.text_view);
        description.setText(getArguments().getString("data_about"));
    }
}
