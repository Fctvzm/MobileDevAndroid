package com.example.assem.catapi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Assem on 10.10.2017.
 */

public class SimpleFragment extends ListFragment {

    private Adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_view, container, false);
        RecyclerView recyclerView = (RecyclerView) view;
        adapter = new Adapter(Cat.cats,getContext());
        recyclerView.setAdapter(adapter);
        return  view;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public SimpleFragment (){}

    public Adapter getAdapter () {
        return this.adapter;
    }
}
