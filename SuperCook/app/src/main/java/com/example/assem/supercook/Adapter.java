package com.example.assem.supercook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Assem on 05.10.2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Recipe> recipes;
    private Context context;
    private FirebaseStorage mFireBaseStorage;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        CardView cv;
        ImageView img;
        TextView rate;
        TextView time;

        public ViewHolder(View itemView){
            super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv);
                title = (TextView)itemView.findViewById(R.id.titleOfMeal);
                img = (ImageView)itemView.findViewById(R.id.imageOfMeal);
                rate = (TextView)itemView.findViewById(R.id.rateOfMeal);
                time = (TextView)itemView.findViewById(R.id.timeToCook);
        }
    }

    public Adapter(ArrayList<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
        mFireBaseStorage = FirebaseStorage.getInstance();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Recipe recipe = recipes.get(position);
        String id = recipe.getId();
        id = id.substring(7);
        final int pos = Integer.parseInt(id) - 1;
        holder.rate.setText(recipe.getRatings() + "");
        holder.title.setText(recipe.getName());
        holder.time.setText(recipe.getTime() + "min");
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RecipeInfoActivity.class);
                intent.putExtra(RecipeFragment.ARG_PARAM, pos);
                context.startActivity(intent);
            }
        });

        StorageReference ref = mFireBaseStorage.getReference().child("images/" + recipe.getImgId());
        Glide.with(context).using(new FirebaseImageLoader())
                .load(ref)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void add (Recipe rec) {
        recipes.add(rec);
        notifyItemInserted(recipes.size() - 1);
    }

}
