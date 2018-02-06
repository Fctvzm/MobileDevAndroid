package com.example.assem.supercook;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private FavAdapter adapter;
    private ArrayList<Recipe> favRecipes;
    private FirebaseDatabase mFireBaseStorage;
    private DatabaseReference favRef;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate (Bundle save) {
        super.onCreate(save);
        Log.d("test", "Oncreate");
        favRecipes = new ArrayList<>();
        mFireBaseStorage = FirebaseDatabase.getInstance();
        favRef = mFireBaseStorage.getReference("savedRecipes").child(MainActivity.userID);
        favRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.exists()) {
                    Query ref = mFireBaseStorage.getReference("recipe").child(dataSnapshot.getKey());
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Recipe recipe = dataSnapshot.getValue(Recipe.class);
                            adapter.add(recipe);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
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

    @Override
    public void onActivityCreated (Bundle save) {
        super.onActivityCreated(save);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("test", "OnCreateView");
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView userName = (TextView)v.findViewById(R.id.profileName);
        userName.setText(MainActivity.curUsername);

        ImageButton exit = (ImageButton) v.findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mFirebaseAuth.signOut();
            }
        });

        RecyclerView list = (RecyclerView)v.findViewById(R.id.recipeListView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        list.setLayoutManager(mLayoutManager);

        adapter = new FavAdapter(favRecipes, getContext());
        list.setAdapter(adapter);
        return v;
    }




}
