package com.example.assem.kbtuapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Assem on 05.10.2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Wall> walls;
    private Context context;
    private String url;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        CardView cv;
        ImageView img;
        TextView date;
        TextView content;

        public ViewHolder(View itemView){
            super(itemView);
                cv = (CardView)itemView.findViewById(R.id.cv);
                title = (TextView)itemView.findViewById(R.id.txtTitle);
                img = (ImageView)itemView.findViewById(R.id.imageView);
                date = (TextView)itemView.findViewById(R.id.txtDate);
                content = (TextView)itemView.findViewById(R.id.txtContent);
        }
    }

    public Adapter (ArrayList<Wall> wallData, Context context) {
        this.walls = wallData;
        this.context = context;
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
        Wall wall = walls.get(position);
        holder.date.setText(wall.str_date());
        holder.title.setText(wall.getTitle());
        holder.content.setText(wall.getFirstPart());
        url = wall.getUrl();
        if (!url.equals("")) {
            Picasso.with(this.context).load(url).into(holder.img);
        }
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EventInfoActivity.class);
                intent.putExtra(MainActivity.POS, position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return walls.size();
    }

}
