package com.example.assem.json_fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assem.json_fragments.SimpleFragment.OnListFragmentInteractionListener;
import com.example.assem.json_fragments.dummy.DummyContent.DummyItem;

import java.util.ArrayList;

/**
 * Created by Assem on 10.10.2017.
 * {@link RecyclerView.Adapter} that can display a {@link Cat} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<Cat> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyRecyclerViewAdapter(ArrayList<Cat> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.simple_layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText(mValues.get(position).getName());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView)view.findViewById(R.id.name);
        }

    }
}
