package com.example.assem.supercook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        int pos = intent.getIntExtra("position", 0);
        String child = String.valueOf(pos);
        database = FirebaseDatabase.getInstance();
        DatabaseReference categoryRef = database.getReference("searchByCategory").child(child);
        final DatabaseReference recipeRef = database.getReference("recipe");

        ImageView imageView = (ImageView)findViewById(R.id.categoryImage);
        imageView.setImageResource(MainActivity.categoryIms[pos]);

        ArrayList<Recipe> categoryRecipes = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycleView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new Adapter(categoryRecipes, this);
        recyclerView.setAdapter(adapter);

        categoryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        recipeRef.child(data.getKey()).addListenerForSingleValueEvent(new ValueEventListener() {
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
