package com.example.assem.supercook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.Serializable;
import java.util.ArrayList;


public class RecipeFragment extends Fragment{

    public static final String ARG_PARAM = "position";
    private int position;
    private FirebaseDatabase mFireBaseDatabase;
    private Adapter mAdapter;

    public RecipeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_PARAM, 0);
        }
        mFireBaseDatabase = FirebaseDatabase.getInstance();
        MainActivity.recipes = new ArrayList<>();
        addListenerRetrieveRecipes();
    }

    private void addListenerRetrieveRecipes () {
        Query query = mFireBaseDatabase.getReference().child("recipe");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        Recipe recipe = data.getValue(Recipe.class);
                        mAdapter.add(recipe);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipe, container, false);

        RecyclerView recView = (RecyclerView) v.findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recView.setLayoutManager(mLayoutManager);

        mAdapter = new Adapter(MainActivity.recipes, getContext());
        recView.setAdapter(mAdapter);
        return v;
    }



    /*private void sendRecipes () {
        Recipe rec = new Recipe(2.7, "You only need 3 ingredients for this delicious cookies", "Classic snickerdoodle cookies",
                "Arrange racks in upper and lower thirds of oven; preheat to 400°F. Line 2 rimmed baking sheets with parchment. Mix sugar, cinnamon, and salt on a large plate. Fill a small bowl or glass with cold water. Roll out pastry between 2 sheets of parchment until short side is 10\" long and dough is about 1/8\" thick. Freeze dough 5 minutes. Using a pizza cutter or sharp knife, cut dough crosswise into 1\" strips. Transfer half of strips to refrigerator. Working one at a time, brush strip lightly with water (or use your fingertips), then immediately transfer to sugar mixture and turn to coat. Starting from the middle, use both hands to twist dough to the ends. Transfer to prepared sheet, arranging twists vertically. Repeat with remaining dough. Freeze 10 minutes. Bake twists, rotating sheets front to back and top to bottom halfway through, until golden brown and beginning to caramelize, 18–20 minutes. Using tongs, immediately lift twists from parchment and let cool with 1 end on rim of baking sheet (work carefully, sugar coating will be very hot).",
                "1 hour, 40 minutes", "Serves 8");
        mFireBaseDatabase.getReference().child("recipe").setValue(rec);

    }*/
}