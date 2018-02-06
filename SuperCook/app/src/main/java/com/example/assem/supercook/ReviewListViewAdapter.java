package com.example.assem.supercook;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by Assem on 28.11.2017.
 */

public class ReviewListViewAdapter extends ArrayAdapter<UserReview> {

    public ReviewListViewAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<UserReview> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView (int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.review_list_view_layout, parent, false);
        }

        UserReview review = getItem(position);

        TextView user = (TextView)view.findViewById(R.id.username);
        user.setText("Username: " + review.getName());

        TextView rating = (TextView)view.findViewById(R.id.userRate);
        rating.setText("Rating: " + review.getPoint());

        TextView text = (TextView)view.findViewById(R.id.userReview);
        text.setText(review.getText());
        return view;
    }
}
