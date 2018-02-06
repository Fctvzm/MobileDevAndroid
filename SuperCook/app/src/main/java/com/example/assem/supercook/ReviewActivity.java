package com.example.assem.supercook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.ui.auth.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ReviewActivity extends AppCompatActivity {

    private FirebaseDatabase mFireBaseStorage;
    private String recId;
    private ArrayList<UserReview> reviews;
    private ReviewListViewAdapter adapter;
    private DatabaseReference reviewRef;
    private double changeRate;
    private double rateOfUser;
    private int position;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Intent intent = getIntent();
        recId = intent.getStringExtra(RecipeInfoActivity.ARG_PARAM);
        rateOfUser = intent.getDoubleExtra(RecipeInfoActivity.RATE, 0);
        position = intent.getIntExtra("position", 0);
        mFireBaseStorage = FirebaseDatabase.getInstance();
        reviews = new ArrayList<>();

        reviewRef = mFireBaseStorage.getReference().child("review/"+recId);


        ListView list = (ListView)findViewById(R.id.reviewList);
        adapter = new ReviewListViewAdapter(this, R.layout.review_list_view_layout, reviews);
        list.setAdapter(adapter);


        final Spinner spinner = (Spinner)findViewById(R.id.spinner);
        final EditText editText = (EditText)findViewById(R.id.editText);

        final Button send = (Button)findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userRating = spinner.getSelectedItem().toString();
                changeRate = Double.parseDouble(userRating);
                UserReview review = new UserReview(changeRate,
                        editText.getText().toString(), MainActivity.curUsername);
                reviewRef.push().setValue(review);
                editText.setText("");
                int size = reviews.size();
                if (size == 0) size++;
                else size = 2;
                changeRate = (changeRate + rateOfUser)/size;
                DatabaseReference reference = mFireBaseStorage.getReference().child("recipe/"+recId+"/ratings");
                reference.setValue(changeRate);
                rateOfUser = changeRate;
                MainActivity.recipes.get(position).setRatings(changeRate);

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.length() > 0) {
                    send.setEnabled(true);
                } else {
                    send.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        retrieveReviews();
    }

    private void retrieveReviews () {
        reviewRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    UserReview review = dataSnapshot.getValue(UserReview.class);
                    adapter.add(review);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
