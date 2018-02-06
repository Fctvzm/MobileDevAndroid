package com.example.assem.json_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class LandFragment extends Fragment {

    public static final String ARG_POSITION = "param1";
    private int position = 0;

    public LandFragment() {
    }

    TextView textView;
    ImageView image;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.land_fragment, container, false);
        textView = (TextView)view.findViewById(R.id.textView);
        image = (ImageView)view.findViewById(R.id.imageView);
        if (Cat.cats.size() > 0) {
            update();
        }
        return view;
    }

    public void updateContent (int position) {
        this.position = position;
        update();
    }
     public void update () {
         textView.setText(Cat.cats.get(position).getName());
         Picasso.with(getContext()).load(Cat.cats.get(position).getUrlImage()).into(image);
     }



}
