package com.example.assem.supercook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class RecipeInfoActivity extends AppCompatActivity {

    private int position;
    public static String ARG_PARAM = "recipeID";
    public static String RATE = "rateOfUser";
    private String recID;
    private FirebaseDatabase mFireBaseStorage;
    private FloatingActionButton fab;
    private boolean added;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_info);
        Intent intent = getIntent();
        position = intent.getIntExtra(RecipeFragment.ARG_PARAM, 0);

        Recipe recipe = MainActivity.recipes.get(position);
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(recipe.getName());

        recID = recipe.getId();
        mFireBaseStorage = FirebaseDatabase.getInstance();

        fab = (FloatingActionButton)findViewById(R.id.fab);
        DatabaseReference Dataref = mFireBaseStorage.getReference("savedRecipes").child(MainActivity.userID);
        Dataref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(recID)){
                    fab.setImageResource(R.drawable.ic_done_white_24dp);
                    added = true;
                }
                else {
                    fab.setImageResource(R.drawable.ic_add_white_24dp);
                    added = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        TextView ingredient = (TextView)findViewById(R.id.ingredients);
        Map<String, String> ingredients = recipe.getIngredients();
        for (String ing : ingredients.values()) {
            ingredient.append("\u2022" + "    " + ing + "\n");
        }

        ImageView img = (ImageView)findViewById(R.id.backdrop);
        FirebaseStorage mFireBaseStorage = FirebaseStorage.getInstance();
        StorageReference ref = mFireBaseStorage.getReference().child("images/" + recipe.getImgId());
        Glide.with(this).using(new FirebaseImageLoader())
                .load(ref)
                .into(img);

        LinearLayout layout = (LinearLayout)findViewById(R.id.linearLayout);
        Map<String, String> preps = recipe.getPreparation();
        int steps = preps.size();
        int i = 1;
        ArrayList<String> values = new ArrayList<>(preps.values());
        Collections.reverse(values);
        for (String step : values) {
            TextView stepOrder = (TextView)LayoutInflater.from(this).inflate(R.layout.step_order_textview, null, false);
            TextView stepPrep = (TextView)LayoutInflater.from(this).inflate(R.layout.step_prep_textview, null, false);
            stepOrder.setText("Step " + i + "/" + steps);
            stepPrep.setText(step);
            layout.addView(stepOrder);
            layout.addView(stepPrep);
            i++;
        }

        String rate = String.valueOf(recipe.getRatings());
        String servings = String.valueOf(recipe.getServings());
        final ForegroundColorSpan fcs = new ForegroundColorSpan(ContextCompat.getColor(this, android.R.color.holo_green_light));
        final RelativeSizeSpan sss = new RelativeSizeSpan(2f);
        final SpannableStringBuilder rateSb = new SpannableStringBuilder(rate+"\nRATINGS");
        final SpannableStringBuilder serveSb = new SpannableStringBuilder(servings+"\nSERVINGS");
        rateSb.setSpan(fcs, 0, rate.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        rateSb.setSpan(sss, 0, rate.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        serveSb.setSpan(fcs, 0, servings.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        serveSb.setSpan(sss, 0, servings.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        TextView review = (TextView)findViewById(R.id.ratings);
        review.setText(rateSb);
        TextView serve = (TextView)findViewById(R.id.servings);
        serve.setText(serveSb);

        ImageView ctgImg = (ImageView)findViewById(R.id.ctgImage);
        TextView ctgText = (TextView)findViewById(R.id.ctgText);

        String [] ctgs = getResources().getStringArray(R.array.categories);
        int ctgId = recipe.getCategory();
        ctgImg.setImageResource(MainActivity.categoryIms[ctgId]);
        ctgText.setText(ctgs[ctgId]);

    }


    public void onClick(View view) {
        Recipe rec = MainActivity.recipes.get(position);
        String recId = rec.getId();
        double rate = rec.getRatings();
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra(RecipeInfoActivity.ARG_PARAM, recId);
        intent.putExtra(RecipeInfoActivity.RATE, rate);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void saveOnClick(View view) {
        DatabaseReference ref = mFireBaseStorage.getReference("savedRecipes").child(MainActivity.userID + "/" + recID);
        if (!added) {
            ref.setValue(true);
            Toast.makeText(this, "This recipes added to favorites", Toast.LENGTH_SHORT).show();
            fab.setImageResource(R.drawable.ic_done_white_24dp);
        } else {
            ref.removeValue();
            Toast.makeText(this, "This recipes deleted from favorites", Toast.LENGTH_SHORT).show();
            fab.setImageResource(R.drawable.ic_add_white_24dp);
        }
    }
}
