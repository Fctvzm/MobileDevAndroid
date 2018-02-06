package com.example.assem.supercook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Assem on 29.11.2017.
 */

public class CtgListViewAdapter extends ArrayAdapter<String> {

    public CtgListViewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String []objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView (final int pos, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.ctg_listview_layout, parent, false);
        }

        String item = getItem(pos);

        TextView text = (TextView)view.findViewById(R.id.ctgTextView);
        ImageView imageView = (ImageView)view.findViewById(R.id.ctgImg);

        text.setText(item);
        imageView.setImageResource(MainActivity.categoryIms[pos]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("position", pos);
                getContext().startActivity(intent);
            }
        });

        return view;
    }
}

