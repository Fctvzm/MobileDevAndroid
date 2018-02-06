package com.example.assem.supercook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        String [] objects = getResources().getStringArray(R.array.categories);
        ListView list = (ListView)view.findViewById(R.id.ctgListView);
        list.setAdapter(new CtgListViewAdapter(getContext(), R.layout.ctg_listview_layout, objects));
        return view;
    }

}
