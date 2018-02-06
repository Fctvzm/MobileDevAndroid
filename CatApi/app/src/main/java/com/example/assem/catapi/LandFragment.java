package com.example.assem.catapi;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Assem on 11.10.2017.
 */

public class LandFragment extends Fragment {

    public LandFragment (){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.land_view, container, false);

        return  view;
    }
}
