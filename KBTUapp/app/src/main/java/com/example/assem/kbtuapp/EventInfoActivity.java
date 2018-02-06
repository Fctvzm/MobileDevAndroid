package com.example.assem.kbtuapp;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EventInfoActivity extends AppCompatActivity {

    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);
        Intent intent = getIntent();
        position = intent.getIntExtra(MainActivity.POS, 0);

        Wall wall = MainActivity.walls.get(position);
        Picasso.with(this).load(wall.getUrl()).into((ImageView)findViewById(R.id.backdrop));
        TextView textView = (TextView)findViewById(R.id.postText);
        textView.setText(wall.getContent());
    }


}
